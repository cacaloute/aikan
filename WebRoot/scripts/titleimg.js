 $(document).ready(function(){
//ËÑË÷°´Å¥
 $("#ss").click(function(){
  var new_li = $("<li>"+ $("#skuang").val() +"</li>");
  $("#d1 ul").append(new_li);
  $("#d1").hide();
  $("#skuang").val("");
 
  })
 
  $("#skuang").focus(function(){
   $("#d1").css("display","block");
  });
   
  $("#skuang").blur();
  $("#qingch").click(function(){
   $("#d1 li:gt(0)").remove();
   $("#d1").hide();
    
  });
 
//ÂÖ²¥Í¼
  var img=$(".img_box img");
  var li=$(".ul5 li");
  var divW=$(".img_box").width();
  var len=$(".img_box img").length;
  img.css("left",divW);
  img.eq(0).css("left",0);
  li.eq(0).css("background","red");
  var index=0;
  var next=0;
  function show(){
   next++;
   if(next==len){
    next=0;
   }
   img.eq(next).css("left",divW);
   img.eq(index).animate({"left":-divW});
   img.eq(next).animate({"left":0});
   li.eq(next).css("background","red");
   li.eq(index).css("background","lawngreen");
   index=next;
  }
  t=setInterval(show,2000);
  function show1(){
   next--;
   if(next==-1){
    next=len-1;
   }
   img.eq(next).css("left",-divW);
   img.eq(index).animate({"left":divW});
   img.eq(next).animate({"left":100});
   li.eq(next).css("background","red");
   li.eq(index).css("background","lawngreen");
   index=next;     
  }
  img.hover(function(){
   clearInterval(t);     
  },function(){
   t=setInterval(show,2000);
  })
  //×óÓÒ°´Å¥
  $(".d2").mousedown(function(){
   clearInterval(t);
   show();
  })
  $(".d2").mousedown(function(){
     t=setInterval(show,2000);
  }) 
  $(".d1").mousedown(function(){
   clearInterval(t);
     show1();
  })
  $(".d1").mousedown(function(){
   t=setInterval(show,2000);
  })
  //Ð¡°×µã µã»÷
  li.mousedown(function(){
   num=$(this).index();
   if(num==next){
    return;
   }else if(num<next){
    clearInterval(t);
    img.eq(num).css("left",-divW);
     img.eq(index).animate({"left":divW});
     img.eq(num).animate({"left":0});
     li.eq(num).css("background","red");
     li.eq(index).css("background","lawngreen");
     index=num;
     next=num;
   }else if(num>next){
    clearInterval(t);
     img.eq(num).css("left",divW);
     img.eq(index).animate({"left":-divW});
     img.eq(num).animate({"left":0});
     li.eq(num).css("background","red");
     li.eq(index).css("background","lawngreen");
     index=num;
     next=num;
   }
 })
    li.mouseup(function(){
     t=setInterval(show,2000);
   })
  })