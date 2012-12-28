// JavaScript Document
<!-- 
	function setTit(name,cursel,n){ 
		for(i=1;i<=n;i++){ 
			var menu=document.getElementById(name+i); 
			//alert(menu);;
			menu.className=i==cursel ? "current" : ""; 
			//alert(menu.className);
		} 
	} 
//-->

<!--
	function setTab(name,cursel,n){
		for(i=1;i<=n;i++){
			var menu=document.getElementById(name+i);
			var con=document.getElementById("con_"+name+"_"+i);
			menu.className=i==cursel?"active":"";
			con.style.display=i==cursel?"block":"none";
		}
	}
//-->
  <!--
  function Pid(id,tag){
      if(!tag){
          return document.getElementById(id);
          }
      else{
          return document.getElementById(id).getElementsByTagName(tag);
          }
  }
  
  function tab(id,hx,box,iClass,s,pr){
	  var wdurl = unescape(window.location.href);
	  var allargs = wdurl.split("-");
	  var npr=allargs[1];
	  //var nid=allargs[2].split(".")[0]-6;
	  var nid2=allargs[2].split(".")[0];
	  //var cla=document.getElementById("p"+npr+nid2);

      var hxs=Pid(id,hx);
      var boxs=Pid(id,box);
      if(!iClass){ // 如果不指定class，则：
          boxsClass=boxs; // 直接使用box作为容器
      }
      else{ // 如果指定class，则：
          var boxsClass = [];
          for(i=0;i<boxs.length;i++){
              if(boxs[i].className.match(/\btab\b/)){// 判断容器的class匹配
                  boxsClass.push(boxs[i]);
              }
          }
      }
      if(!pr){ // 如果不指定预展开容器，则：
          go_to(npr); // 默认展开序列
          yy();
      }
      else {
          go_to(pr);
          yy();
      }
      function yy(){
          for(var i=0;i<hxs.length;i++){
              hxs[i].temp=i;
              if(!s){// 如果不指定事件，则：
                  s="onmouseover"; // 使用默认事件
                  hxs[i][s]=function(){
                      go_to(this.temp);
                  }
              }
              else{
                  hxs[i][s]=function(){
                      go_to(this.temp);
                  }
              }
          }
      }
      function go_to(pr){
          for(var i=0;i<hxs.length;i++){
              if(!hxs[i].tmpClass){
                  hxs[i].tmpClass=hxs[i].className+="";
                  boxsClass[i].tmpClass=boxsClass[i].className+="";
              }
              if(pr==i){
                  //hxs[i].className+=" up"+; // 展开状态：标题
                  hxs[i].className+=" up"; // 展开状态：标题
                  boxsClass[i].style.display="block";; // 展开状态：容器
				  boxsClass[npr].id="this";
				  var ulid=Pid("this","a");
				  ulid[nid2].style.color="red";
				  boxsClass[i].scrollTop=26*nid2;
				  //cla.className="cla";
              }
              else {
                  hxs[i].className="";
                  boxsClass[i].style.display="none";
              }
          }
      }
  }
