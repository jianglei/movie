 <div class="code_body">
    <!--                                http://ex.mobmore.com/-->
    <h1>这个广告位的ID为:<span style="color:red" class="adslot_id"></span></h1>
    
    <h1>一、WAP集成方式地址:</h1>&nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank" id="code_review" base_url="/api/wap?slot_id=" href="#">
     请点击生成代码</a>
     
    <h1>二、SDK集成方式说明:</h1>
     
    <h2>1. 将 zip 压缩包中的 umeng_appnetwork_android_sdk.jar 导入工程</h2>
    <h2>2. 将 zip 压缩包中的 res 文件夹拷贝到工程目录下,和你工程现有的 res 文件夹合并。</h2>
    <p>(SDK 里的资源文件名都是以" exchange_"开头, 请确保你工程中没有以" exchange_"开头的资源文件。)</p>
    <h2>3. 打开 Android 工程中文件 AndroidManifest.xml</h2>
    <h3>a) 添加 appkey (UFP用户不需要添加appkey, 在集成时使用slot_id 来表示广告位)</h3>

    <p>在&lt;application&gt;中添加</p>
    <pre class="brush: xml">     &lt;meta-data android:name="UMENG_SLOTID" android:value="xxxxxxxx" /&gt;</pre>
    <p> 其中 xxxxxxxx 是您在友盟网站上为应用程序注册之后获得的广告位ID,请将真实的slotid替换掉 <span class="adslot_id">xxxxxxxx</span>。
        注意:meta-data 要小写,不要大写。</p>
    <h3> b) 添加渠道 (用于反馈应用的下载渠道)</h3>
    <p>在&lt;application&gt;中添加</p>
    <pre class="brush: xml">     &lt;meta-data android:name="UMENG_CHANNEL" android:value="xxxxxxxx" /&gt;</pre>
    <p> 其中 xxxxxxxx 是您在友盟网站上为应用程序注册之后获得的广告位ID,请将真实的slotid替换掉 <span class="adslot_id">xxxxxxxx</span>。
        注意:meta-data 要小写,不要大写。</p>
    <h3> c) 添加访问权限</h3>
    <pre class="brush: xml">
        &lt;uses-permission android:name="android.permission.INTERNET" /&gt;
        &lt;uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /&gt;
        &lt;uses-permission android:name="android.permission.ACCESS_WIFI_STATE" /&gt;
        &lt;uses-permission android:name="android.permission.READ_PHONE_STATE" /&gt;
        &lt;uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /&gt;
    </pre>
    <h3> d) 声明SDK用到的下载服务</h3>
    <pre class="brush: xml">
       &lt;service android:exported="true"
            android:name="com.exchange.common.DownloadingService"
            android:process=":DownloadingService"&gt;
        &lt;/service&gt;
    </pre>
    <h3>下面是一个完整的AndroidManifest.xml文件的例子</h3>
    <pre class="brush: xml">
        &lt;?xml version="1.0" encoding="utf-8"?&gt;
        &lt;manifest xmlns:android="http://schemas.android.com/apk/res/android"
              package="com.test.umeng" android:versionCode="1" android:versionName="1.0"&gt;
              &lt;application android:icon="@drawable/icon" android:label="@string/app_name"&gt;
                   &lt;activity android:name=".UmengExchangeDemo" android:label="@string/app_name"&gt;
                         &lt;intent-filter&gt;
                              &lt;action android:name="android.intent.action.MAIN" /&gt;
                              &lt;category android:name="android.intent.category.LAUNCHER" /&gt;
                         &lt;/intent-filter&gt;
                   &lt;/activity&gt;          
                &lt;service android:exported="true"
                    android:name="com.exchange.common.DownloadingService"
                    android:process=":DownloadingService"&gt;
                &lt;/service&gt;
                &lt;meta-data android:value="4e2f707f431fe371c4000242"
                         android:name="UMENG_APPKEY"&gt;&lt;/meta-data&gt;
                &lt;meta-data android:value="Android Market"
                         android:name="UMENG_CHANNEL"&gt;&lt;/meta-data&gt;
              &lt;/application&gt;
              &lt;uses-permission android:name="android.permission.INTERNET" /&gt;
              &lt;uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /&gt;
              &lt;uses-permission android:name="android.permission.ACCESS_WIFI_STATE" /&gt;
              &lt;uses-permission android:name="android.permission.READ_PHONE_STATE" /&gt;
              &lt;uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /&gt;
        &lt;/manifest&gt;
    </pre>
    <h2>4. 显示推广应用：</h2>
    <p>在需要添加友盟应用联盟推广的Activity的onCreate()函数中添加：</p>
    <pre class="brush: java">
        public class BannerExample extends Activity {
              /** Called when the activity is first created. */
              @Override
              public void onCreate(Bundle savedInstanceState) {
                   super.onCreate(savedInstanceState);
                   setContentView(R.layout.banner_activity);        
                   // 找到一个添加banner 的父亲节点，将banner View 附着到这个节点上
                   ViewGroup parent = (ViewGroup)this.findViewById(R.id.parent);     

                   /* 应用联盟集成方式， 请在AndroidManifest.xml中添加 UMENG_APPKEY */
                   ExchangeDataService service = new ExchangeDataService();
                   ExchangeViewManager viewMgr = new ExchangeViewManager(this, service);
                   viewMgr.addView(parent, ExchangeConstants.type_standalone_handler);
                   /*
                    * UFP集成方式，传入slot_id 参数        
                   ExchangeDataService service1 = new ExchangeDataService("39648");
                   ExchangeViewManager viewMgr = new ExchangeViewManager(this, service1);
                   viewMgr.addView(parent, ExchangeConstants.type_standalone_handler);
                   */   
              }
        }
    </pre>
    <p>下面的例子是对应于上面的BannerExample Activity的布局文件(banner_activity.xml):</p>
    <pre class="brush: xml">
        &lt;?xml version="1.0" encoding="utf-8"?&gt;
        &lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:layout_width="fill_parent" android:layout_height="fill_parent"
              android:background="@android:color/darker_gray"
              android:id="@+id/parent"&gt;
        &lt;/RelativeLayout&gt;
    </pre>
    
    <h2>5. 选择显示样式:</h2>
    <div class="banner landing_type">
        <h3>横幅样式</h3>
        <p>添加独立的 banner,可放置在页面任何地方,点击后在新的页面展示详细信息</p>
        <pre class="brush: java">
            ExchangeViewManager exchangeViewManager = new ExchangeViewManager();
            ExchangeViewManager viewMgr = new ExchangeViewManager(context, service);
            exchangeViewManager.addView(this, rootLayout, ExchangeConstants.type_standalone_handler);
        </pre>
    </div>
    <div class="custom landing_type">
        <h3>自定义入口</h3>
        <h3>a)静态图标</h3>
        <p>在需要展示小把手的Activity 样式文件添加一个ImageView ，添加宽度，高度，图片等属性:</p>
        <pre class="brush: java">
            ImageView imageview = (ImageView) findViewById(R.id.image_view_id);
            Drawable drawable = context.getResources().getDrawable(R.drawable.drawable_id);
            new ExchangeViewManager(context, new ExchangeDataService(slot_id)).addView(ExchangeConstants.type_list_curtain, imageview, drawable);
        </pre>
        <h3>a)动态图标</h3>
        <p>在需要展示小把手的Activity 样式文件添加一个ImageView ，添加宽度，高度，图片等属性：
            (注意：该样式不需要在ImageView中指定图片。但必须在云端配置图片，否则将不显示)</p>
        <pre class="brush: java">
            ImageView imageview = (ImageView) findViewById(R.id.image_view_id);
            new ExchangeViewManager(context, new ExchangeDataService(slot_id))
                             .addView(ExchangeConstants.type_list_curtain, imageview);
        </pre>
    </div>
    <div class="bigimage landing_type">
        <h3>大图</h3>
        <p>只能取数据，没有UI展示</p>
        <pre class="brush: java">
            ExchangDataService dataService = new ExchangeDataService();
            dataService.displayStyle = 1; 
            ExchangeDataRequestListener dataReceiveListener = new ExchangeDataRequestListener()
            {
               public void dataReceived(int status, List<AdvertiserConfig> data)
               {
                     // handle received data.
               }
            }
            dataService.requestDataAsyn(context,    dataReceiveListener) ;
        </pre>
        <p>支持高清图片，640*320象素大小。可以点击进入广告详情页。</p>
    </div>
    <div class="embed landing_type">
        <h3>内嵌入口</h3>
        <pre class="brush: java">
            ViewGroup fatherLayout = (ViewGroup) this.findViewById(R.id.ad);
            ListView listView = (ListView) this.findViewById(R.id.list);
            ExchangeViewManager exVmr = new ExchangeViewManager(this,new ExchangeDataService(slot_id));
            exVmr.addView(fatherLayout, listView);
        </pre>
        <p>对应的布局文件如下：</p>
        <pre class="brush: xml">
            &lt;RelativeLayout android:layout_width="-1dp"  android:layout_height="-2dp"&gt;
               &lt;RelativeLayout android:layout_width="-1dp"
                     android:layout_height="-2dp" android:id="@+id/tab1"
                     android:layout_marginBottom="5dp" android:layout_marginLeft="5dp"
                     android:layout_marginRight="5dp"&gt;
                     &lt;ListView android:id="@+id/list1" android:cacheColorHint="#00000000"
                          android:layout_width="fill_parent" android:dividerHeight="1px"
                          android:divider="#dedfde" android:listSelector="#00000000"
                          android:layout_height="-2dp"&gt;
                     &lt;/ListView&gt;
               &lt;/RelativeLayout&gt;
          &lt;/RelativeLayout&gt;
        </pre>
    </div>
    
    <div class="wap landing_type">
        <h3>WAP</h3>
        <pre class="brush: java">
            ExchangeViewManager exchangeViewManager = new ExchangeViewManager();
            exchangeViewManager.addView(context, null, ExchangeConstants.type_cloud_full);
        </pre>
    </div>
    <div class="text landing_type">
        <h3>WAP</h3>
        <p>在需要展示文字链的Activity 样式文件添加一个RelativeLayout 作为rootView 文字链长度填充rootView,高度将按照文字指定大小显示:</p>
        <pre class="brush: java">
            ExchangeViewManager exchangeViewManager =
            new ExchangeViewManager(context, new ExchangeDataService(soltId));
            exchangeViewManager.addView(rootView, ExchangeConstants.type_hypertextlink_banner);
        </pre>
        <p>如果需要指定文字大小 ，以下配置文字将以18sp大小显示:</p>
        <pre class="brush: java">
            exchangeViewManager.addView(rootView, ExchangeConstants.type_hypertextlink_banner，”18”);
        </pre>
        <p>设置轮播时间，在exchangeViewManager.addView 之前添加如下，时间单位毫秒:</p>
        <pre class="brush: java">
            exchangeViewManager.setHypertextlinkDuration(3000);
        </pre>
    </div>
   <h2>6. 混淆注意事项:</h2>
   <p>由于sdk需要引用导入工程的资源文件，通过了反射机制得到资源引用文件R.java，但是在开发者通过proguard等混淆/优化工具处理.apk时，proguard可能会将R.java删除，如果遇到这个问题，请在proguard配置文件中添加keep命令如：</p>
   <pre class="brush: java">
        -keep public class [package name].R$*{
             public static final int exchange_*;
        }    
   </pre>
   <p>这里[package name]是你的应用程序的包名（替换的时候请删除方括号）。例如，如果程序的package name叫com.demo, 那么就改为:</p>
   <pre class="brush: java">
        -keep public class com.demo.R$*{
             public static final int exchange_*;
        }  
   </pre>
   <p>
       <strong>注意:</strong>在非中文手机上，默认不展示推广内容。
       如果想在非中文手机上显示推广内容， 在请求数据之前将ExchangeConstants.ONLY_CHINESE设置为false。
   </p>
   <pre class="brush: java">
        public void onCreate(Bundle savedInstanceState) {
                   super.onCreate(savedInstanceState);
                   requestWindowFeature(Window.FEATURE_NO_TITLE);
                   setContentView(R.layout.splash_activity);
                   ExchangeConstants.ONLY_CHINESE=false;
        ViewGroup fatherLayout1 = (ViewGroup) this.findViewById(R.id.tab1);
        ListView listView1 = (ListView) this.findViewById(R.id.list1);
        new ExchangeViewManager().addView(this, fatherLayout1, listView1);
        }
   </pre>
   <h2>7. 定制化变量</h2>
   <p>可以通过设置下面的变量改变SDK默认的界面或者行为:</p>
   <ol>
       <li> ExchangeConstants.full_screen: 显示全屏推荐时是否隐藏系统工具栏</li>
       <li> ExchangeConstants.blur_switcher: 弹出窗口后是否使用阴影遮挡其他部分</li>
       <li> ExchangeConstants.ONLY_CHINESE: 是否在非中文环境下展示，默认关闭</li>
       <li> ExchangeConstants.banner_alpha：如果使用standAlone模式，可设置banner的透明度</li>
       <li> 隐藏推广栏: exchangeViewManager.hideBanner();</li>
       <li> 设置底部小把手的icon不占整行:ExchangeConstants.handler_auto_expand = false;</li>
       <li> 如果想要修改默认的列表元素显示样式， 可以修改文件
            对于嵌入式List： exchange_container_banner.xml
            对于置顶/底下把手：exchange_normal_banner.xml
            注意不要改变这两个文件里面元素的id， 但是可以改变他们的属性， 比如，android:visible, 字体颜色，大小等。
       </li>
       <li>
           如果想实现分组功能， 可以传递一个ExchangeDataService给ExchangeViewManager， 示例如下:
           <pre class="brush: java">
               ExchangeDataService exchangeDataService1 = new ExchangeDataService();
               exchangeDataService1.setKeywords("app"); // 设置分组的关键词。
               exchangeDataService1.autofill=0; //自主广告数量小的情况下，不要自动填充来自交往网络的广告。
               new ExchangeViewManager(exchangeDataService1).addView(this,  fatherLayout1, listView1);
           </pre>
       </li>
       <li>
           如果想在Logcat里面打印log，ExchangeConstants.DEBUG_MODE=true; 默认不打印Log。
       </li>
   </ol>
   <h2>示例程序</h2>
   <p>请到友盟网站下载完整的示例程序。</p>
<!--                               <h2>说明</h2>
   <ol>
       <li>
           <h3>权限说明</h3>
           <table>
               <tr>
                   <td>android.permission.INTERNET</td>
                   <td>向友盟的服务器请求应用推广数据。</td>
               </tr>
               <tr>
                   <td>android.permission.ACCESS_NETWORK_STATE</td>
                   <td>检测网络状态。</td>
               </tr>
               <tr>
                   <td>android.permission.READ_PHONE_STATE</td>
                   <td>
                       这个权限仅为了获取用户手机的IMEI，利用哈希生成一个UUID，作为唯一的标识用户。
                       (如果您的应用会运行在无法读取IMEI的平板上，我们会将mac地址作为用户的唯一标识，请添加权限：android.permission.ACCESS_WIFI_STATE)
                   </td>
               </tr>
               <tr>
                   <td>android.permission.WRITE_EXTERNAL_STORAGE</td>
                   <td>缓存下载的应用到SD卡。</td>
               </tr>
           </table>
       </li>
       <li>
           <h3>技术支持</h3>
           <p>如果您在集成友盟应用联盟SDK的过程中遇到任何问题， 欢迎联系我们的技术工程师。如果您有好的建议，也欢迎和我们交流。</p>
       </li>
   </ol>-->
   
   <h1>三、API方式接入</h1> 
    <h2>测试接口为：</h2>
    <a base_url="/api/data?slot_id=" href="#" target="_blank" id="code_review2"></a>
    <p>参数：slot_id为分配的一个广告位id，request_count可以指定要显示的广告数目
        数据格式协议说明如下（JSON格式）：</p>
    <pre class="brush: java">
        {
            status, int(0: succeed, 1: failed)
            promoters, array
            [
                {
                    title, string：应用名称
                    icon, string：图标的URL
                    description, string 详细描述信息
                    provider,string：开发者
                    img,string：Banner图片

                    app_package_name, string：推荐的应用包名
                    app_version_code, string: 应用的版本

                    detail_url,string：详情页面URL
                    down_url,       string:         下载地址
                },
            … …
            ]
        } 
    </pre>
</div>