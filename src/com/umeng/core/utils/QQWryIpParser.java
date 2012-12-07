package com.umeng.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class QQWryIpParser {

	/**
	 *  * 用来读取QQwry.dat文件，以根据ip获得好友位置，QQwry.dat的格式是
	 * 一. 文件头，共8字节
	 *        1. 第一个起始IP的绝对偏移， 4字节
	 *     2. 最后一个起始IP的绝对偏移， 4字节
	 * 二. "结束地址/国家/区域"记录区
	 *     四字节ip地址后跟的每一条记录分成两个部分
	 *     1. 国家记录
	 *     2. 地区记录
	 *     但是地区记录是不一定有的。而且国家记录和地区记录都有两种形式
	 *     1. 以0结束的字符串
	 *     2. 4个字节，一个字节可能为0x1或0x2
	 *           a. 为0x1时，表示在绝对偏移后还跟着一个区域的记录，注意是绝对偏移之后，而不是这四个字节之后
	 *        b. 为0x2时，表示在绝对偏移后没有区域记录
	 *        不管为0x1还是0x2，后三个字节都是实际国家名的文件内绝对偏移
	 *           如果是地区记录，0x1和0x2的含义不明，但是如果出现这两个字节，也肯定是跟着3个字节偏移，如果不是
	 *        则为0结尾字符串
	 * 三. "起始地址/结束地址偏移"记录区
	 *     1. 每条记录7字节，按照起始地址从小到大排列
	 *        a. 起始IP地址，4字节
	 *        b. 结束ip地址的绝对偏移，3字节
	 *
	 * 注意，这个文件里的ip地址和所有的偏移量均采用little-endian格式，而java是采用
	 * big-endian格式的，要注意转换
	 */
    protected static Log log = LogFactory.getLog(QQWryIpParser.class);

    private static final String IP_FILE_PATH = "/qqwry.dat";
    /**
     * 使用方法 QQWry w = new QQWry(); w.getCountry() + " " + w.getLocal()
     */

    private String Country, LocalStr;
    private long IPN;
    private int RecordCount, CountryFlag;
    private long RangE, RangB, OffSet, StartIP, EndIP, FirstStartIP,
            LastStartIP, EndIPOff;
    private byte[] buff;

    // 内存映射文件
    public static MappedByteBuffer mbb;

    // 文件指针
    private long pos = 0L;

    // 文件长度
    private static long l;

    private long B2L(byte[] b) {
        long ret = 0;
        for (int i = 0; i < b.length; i++) {
            long t = 1L;
            for (int j = 0; j < i; j++)
                t = t * 256L;
            ret += ((b[i] < 0) ? 256 + b[i] : b[i]) * t;
        }
        return ret;
    }

    private long ipToInt(String ip) {
        String[] arr = ip.split("\\.");
        long ret = 0;
        for (int i = 0; i < arr.length; i++) {
            long l = 1;
            for (int j = 0; j < i; j++)
                l *= 256;
            try {
                ret += Long.parseLong(arr[arr.length - i - 1]) * l;
            } catch (Exception e) {
                ret += 0;
            }
        }
        return ret;
    }

    public void seek(String ip) {
    	String path = QQWryIpParser.class.getResource("/").getPath();
    	
    	seek(ip, path);
    }

    public void seek(String ip, String path) {

        this.IPN = ipToInt(ip);
        try {
        	//TODO: useless for qqway.dat in jar
            // 映射IP信息文件到内存中
            if (mbb == null) {
//            	FileInputStream is = QQWryIpParser.class.getResourceAsStream(IP_FILE_PATH);
            	
                RandomAccessFile fis = new RandomAccessFile(path+"qqwry.dat", "r");
                l = fis.length();
                FileChannel fc = fis.getChannel();
                mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, l).load();
                mbb.order(ByteOrder.LITTLE_ENDIAN);
                fis.close();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
		}
        // buff = new byte[4];
        try {
            // mbb.position(0);
            this.getByteBuffer(4);
            // mbb.get(buff);
            FirstStartIP = this.B2L(buff);
            this.getByteBuffer(4);
            LastStartIP = this.B2L(buff);
            RecordCount = (int) ((LastStartIP - FirstStartIP) / 7);
            if (RecordCount <= 1) {
                LocalStr = Country = "未知";
                return;
            }

            RangB = 0;
            RangE = RecordCount;
            long RecNo;

            do {
                RecNo = (RangB + RangE) / 2;
                getStartIP(RecNo);
                if (IPN == StartIP) {
                    RangB = RecNo;
                    break;
                }
                if (IPN > StartIP)
                    RangB = RecNo;
                else
                    RangE = RecNo;
            } while (RangB < RangE - 1);

            getStartIP(RangB);
            getEndIP();
            getCountry(IPN);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private long getEndIP() throws IOException {
        try {
            this.pos = EndIPOff;
        } catch (Exception e) {
            System.out.println("错误的位置：" + EndIPOff);
            e.printStackTrace();
            throw new IOException();
        }
        this.getByteBuffer(4);
        EndIP = this.B2L(buff);
        this.getByteBuffer(1);

        CountryFlag = (buff[0] < 0) ? 256 + buff[0] : buff[0];
        return EndIP;
    }

    private long getStartIP(long RecNo) throws IOException {
        try {
            OffSet = FirstStartIP + RecNo * 7;
            this.pos = OffSet;
        } catch (Exception e) {
            System.out.println("错误的位置：" + OffSet);
            e.printStackTrace();
            throw new IOException();
        }
        this.getByteBuffer(4);
        StartIP = this.B2L(buff);
        this.getByteBuffer(3);
        EndIPOff = this.B2L(buff);
        return StartIP;
    }

    private void getCountry(long ip) throws IOException {
        if (CountryFlag == 1 || CountryFlag == 2) {
            Country = getFlagStr(EndIPOff + 4);
            if (CountryFlag == 1) {
                LocalStr = getFlagStr(pos);
                if (IPN >= ipToInt("255.255.255.0")
                        && IPN <= ipToInt("255.255.255.255")) {
                    LocalStr = getFlagStr(EndIPOff + 21);
                    Country = getFlagStr(EndIPOff + 12);
                }
            } else {
                LocalStr = getFlagStr(EndIPOff + 8);
            }
        } else {
            Country = getFlagStr(EndIPOff + 4);
            LocalStr = getFlagStr(pos);
        }
    }

    private String getFlagStr(long OffSet) throws IOException {
        int flag = 0;
        do {
            this.pos = OffSet;
            this.getByteBuffer(1);
            flag = (buff[0] < 0) ? 256 + buff[0] : buff[0];
            if (flag == 1 || flag == 2) {
                this.getByteBuffer(3);
                if (flag == 2) {
                    CountryFlag = 2;
                    EndIPOff = OffSet - 4;
                }
                OffSet = this.B2L(buff);
            } else
                break;
        } while (true);

        if (OffSet < 12) {
            return "";
        } else {
            this.pos = OffSet;
            return getStr();
        }
    }

    private String getStr() throws IOException {
        ByteArrayOutputStream byteout = new ByteArrayOutputStream();
        byte c = this.getByteBuffer(-1);
        do {
            byteout.write(c);
            c = this.getByteBuffer(-1);
        } while (c != 0 && pos < l);
        return byteout.toString("GBK");
    }

    public String getLocal() {
        return this.LocalStr;
    }

    public String getCountry() {
        return this.Country;
    }

    /**
     * 从内存映射文件读取数据的时候控制线程同步
     */
    private byte getByteBuffer(int length) {
        synchronized (mbb) {
            mbb.position(Integer.parseInt(Long.toString(this.pos)));
            if (length > 0) {
                this.buff = new byte[length];
                mbb.get(buff);
                this.pos = mbb.position();
                return buff[0];
            }
            byte c = mbb.get();
            this.pos = mbb.position();
            return c;
        }
    }
}
