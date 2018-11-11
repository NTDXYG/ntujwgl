# ntujwgl
南通大学教务管理的模拟登陆、自动测评和爬取数据等等  
模拟登陆：使用htmlunit，  
  1、定义自己的学号、身份证号、密码  
  2、将学号、身份证号、密码赋值到页面表单元素中  
  3、获取验证码，使用HtmlImage来获取验证码图片  
  4、识别验证码，用了2个方法：人工识别和OCR  
  5、模拟登陆，并获取返回的sessionid  
自动测评/调查问卷：  
  1、人工提交一份，获取url地址，get/post方法以及data  
  2、将data信息（一般30个左右）复制到txt文本里，类似map，使用FileReader获取data信息  
  3、使用jsoup提交并接受返回信息(提交时要带上sessionid)，成功返回“success：true”，否则返回“success：false”  
爬取数据：  
  1、查看url，get/post和data  
  2、jsoup获取并做相关逻辑  
具体使用信息关注我博客，以后有空会更新  
https://home.cnblogs.com/u/yg1024/
