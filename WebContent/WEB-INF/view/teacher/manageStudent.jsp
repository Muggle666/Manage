<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery.validate.min.js"></script>
		<!-- 文件上传 -->
		<script src="${pageContext.request.contextPath}/static/assets/js/ajaxfileupload.js"></script>
		<title>在线考试</title>
		
		<script>
			$(function(){
				$("#updateTestCloseBtn").click(function(){
					
				});
				$(".delete-test").each(function(){
			 		   $(this).click(function(){
			 			  if(confirm("确定要删除吗？")){
			 			  }else{
			 				  $(this).attr("href","");
			 			  }
			  		  })
			    });
				 //显示上传控制样式
				$('#uploadFile').ace_file_input({
					style:'well',
					btn_choose:'Drop files here or click to choose',
					btn_change:null,
					no_icon:'ace-icon fa fa-cloud-upload',
					droppable:true,
					thumbnail:'small',
					preview_error:function(filename,error_code){
						}
					}).on('change',function(){
						});
				

				//弹出批量添加的弹出框
				$('#batchAddStudentBtn').click(function(){
					$('#uploadFileModel').modal('show');
				});
				//弹出添加的弹出框
				$('#addStudentBtn').click(function(){
					$('#addStudentModal').modal('show');
				});
				$('#addAll').on('click',function(){
					$.ajaxFileUpload({
						url:'${contextPath}/Manage/teacher/uploadFile',
						secureuri:false,
						fileElementId:'uploadFile',
						dataType:'json',
						complete:function(data){
							},
							success:function(data,status){
								if(data.success){
									alert('成功');
								}else{
									alert(data.messageMap.message);
								}
							},
							error:function(data,status,e){
								window.location.reload();
								alert('添加成功');
							}
						});
					});

				$("#addSaveBtn").click(function(){
					if(true){
						var userId = $("#addTestForm input[name='userId']").val();
						var userName = $("#addTestForm input[name='userName']").val();
						var gender= $("#addTestForm input[name='gender']").val();
						var tel= $("#addTestForm input[name='tel']").val();
						var email= $("#addTestForm input[name='email']").val();
						var address= $("#addTestForm input[name='address']").val();
						var birthday= $("#addTestForm input[name='birthday']").val();
						$.ajax({
							type: 'POST',
							url: '${contextPath}/Manage/teacher/saveUser',
							data: {
								userId: userId,
								userName: userName,
								gender: gender,
								tel: tel,
								email: email,
								address: address,
								birthday: birthday
							},
							dataType: 'json',
							success: function(data){
								if(data.success){
									alert("添加成功！");
									window.location.reload();
								}else{
									var messageMap = data.messageMap;
									for(var key in messageMap){
										var messageMap = messageMap[key];
//										$("#addTestForm input[name=" + key + "]").after('<label class="jsr303-message-label">' + value +'</label>');
										alert("添加失败！ - " + messageMap['message']);
									}
								}
							}
						});
					}
				});
				//修改
					$(".edit-test").click(function(){
					 var userId = $(this).parent().siblings().eq(1).text();
					 var userName = $(this).parent().siblings().eq(2).text();
				     var gender = $(this).parent().siblings().eq(3).text();
					 var tel = $(this).parent().siblings().eq(4).text();
					 var email = $(this).parent().siblings().eq(5).text();
					 var address = $(this).parent().siblings().eq(6).text();
					 var birthday = $(this).parent().siblings().eq(7).text();
					 $("#updateForm input[name='userId']").val(userId);
						$("#updateForm input[name='userName']").val(userName);
						$("#updateForm input[name='gender']").val(gender)
						$("#updateForm input[name='tel']").val(tel);
						$("#updateForm input[name='email']").val(email);
						$("#updateForm input[name='address']").val(address);
						$("#updateForm input[name='birthday']").val(birthday);					
				}); 
				
				//修改ajax
				$("#updateSaveBtn").click(function(){
					if(true){
						var userId = $("#updateForm input[name='userId']").val();
						var userName = $("#updateForm input[name='userName']").val();
						var gender= $("#updateForm input[name='gender']").val();
						var tel= $("#updateForm input[name='tel']").val();
						var email= $("#updateForm input[name='email']").val();
						var address= $("#updateForm input[name='address']").val();
						var birthday= $("#updateForm input[name='birthday']").val();
						$.ajax({
							type: 'POST',
							url: '${contextPath}/Manage/teacher/editUser',
							data: {
								userId: userId,
								userName: userName,
								gender: gender,
								tel: tel,
								email: email,
								address: address,
								birthday: birthday
							},
							dataType: 'json',
							success: function(data){
								if(data.success){
									alert("修改成功！");
									window.location.reload();
								}else{
									var messageMap = data.messageMap;
									for(var key in messageMap){
										var messageMap = messageMap[key];
//										$("#addTestForm input[name=" + key + "]").after('<label class="jsr303-message-label">' + value +'</label>');
										alert("修改失败！ - " + messageMap['message']);
									}
								}
							}
						});
					}
				});
			});
		</script>
		
		
		<meta name="description" content="Static &amp; Dynamic Tables" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->

		<!-- text fonts -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/ace-fonts.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/ace.min.css" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/ace-part2.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="${pageContext.request.contextPath}/static/assets/js/ace-extra.min.js"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="${pageContext.request.contextPath}/static/assets/js/html5shiv.min.js"></script>
		<script src="${pageContext.request.contextPath}/static/assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="no-skin">
		<!-- #section:basics/navbar.layout -->
	
			<!-- /section:basics/sidebar -->
			<div class="main-content">
				<!-- #section:basics/content.breadcrumbs -->
			

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<!-- #section:settings.box -->
					<div class="ace-settings-container" id="ace-settings-container">
						<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
							<i class="ace-icon fa fa-cog bigger-150"></i>
						</div>

						<div class="ace-settings-box clearfix" id="ace-settings-box">
							<div class="pull-left width-50">
								<!-- #section:settings.skins -->
								<div class="ace-settings-item">
									<div class="pull-left">
										<select id="skin-colorpicker" class="hide">
											<option data-skin="no-skin" value="#438EB9">#438EB9</option>
											<option data-skin="skin-1" value="#222A2D">#222A2D</option>
											<option data-skin="skin-2" value="#C6487E">#C6487E</option>
											<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
										</select>
									</div>
									<span>&nbsp; Choose Skin</span>
								</div>

								<!-- /section:settings.skins -->

								<!-- #section:settings.navbar -->
								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
									<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
								</div>

								<!-- /section:settings.navbar -->

								<!-- #section:settings.sidebar -->
								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
									<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
								</div>

								<!-- /section:settings.sidebar -->

								<!-- #section:settings.breadcrumbs -->
								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
									<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
								</div>

								<!-- /section:settings.breadcrumbs -->

								<!-- #section:settings.rtl -->
								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
									<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
								</div>

								<!-- /section:settings.rtl -->

								<!-- #section:settings.container -->
								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
									<label class="lbl" for="ace-settings-add-container">
										Inside
										<b>.container</b>
									</label>
								</div>

								<!-- /section:settings.container -->
							</div><!-- /.pull-left -->

							<div class="pull-left width-50">
								<!-- #section:basics/sidebar.options -->
								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" />
									<label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" />
									<label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
								</div>

								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight" />
									<label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
								</div>

								<!-- /section:basics/sidebar.options -->
							</div><!-- /.pull-left -->
						</div><!-- /.ace-settings-box -->
					</div><!-- /.ace-settings-container -->

					<!-- /section:settings.box -->
					<div class="page-content-area">
						<div class="page-header">
							<h1>
								Tables
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									在线考试
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="row">
<div id="messageDiv">
<c:if test="${not empty message}">
		<script>
			alert('${message}');
		</script>
</c:if>
</div>
									<div class="col-xs-12">
										<button id="addStudentBtn" class="btn btn-primary">
									<i class="ace-icon fa fa-plus align-top bigger-125"></i>
									添加学生信息
									</button>
									<button id="batchAddStudentBtn" class="btn btn-primary">
									<i class="ace-icon fa fa-plus align-top bigger-125"></i>
									批量添加学生信息
									</button>
										<table id="sample-table-1" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="center">
														<label class="position-relative">
															<input type="checkbox" class="ace" />
															<span class="lbl"></span>
														</label>
													</th>
													<th>UserId</th>
													<th>Name</th>
													<th>Gender</th>
													<th>Tel</th>
													<th>Email</th>
													<th>Address</th>
													<th>Birthday</th>
													<th>Action</th>
													
												</tr>
											</thead>

											<tbody>
											
	 <c:forEach items="${userList}" var="user">
        <tr>
        <td class="center">
		<label class="position-relative">
		<input type="checkbox" class="ace" />
		<span class="lbl"></span>
		</label>
		</td>
            <td>${user.userId}</td>
            <td>${user.userName}</td>
            <td>${user.gender}</td>
             <td>${user.tel}</td>
             <td>${user.email}</td>
            <td>${user.address}</td>
            <td>${user.birthday}</td>
            <td>
            <a class="btn btn-xs btn-primary ace-icon fa fa-pencil edit-test" data-toggle="modal" data-target="#updateModal">
            </a>
            &nbsp;
             <a id="delete" class="btn btn-xs btn-primary ace-icon fa fa-trash-o delete-test" href="deleteUser/${user.userId}">
              </a>
            </td>
        </tr>
    </c:forEach>
											</tbody>
										</table>
									</div><!-- /.span -->
								</div><!-- /.row -->
		<!--批量添加 模态框（Modal） -->
		<div class="modal fade" id="uploadFileModel" tabindex="-1" role="dialog" aria-labelledby="uploadFileModalLable" aria-hidden="true">
  			  <div class="modal-dialog">
   			     <div class="modal-content">
         			   <div class="modal-header">
             				   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
             				   <h4 class="modal-title" id="uploadFileModalLable">上传</h4>
          			  </div>
           			  <div class="modal-body">
           			  	<input id="uploadFile" name="uploadFile" type="file">
           			  </div>
            		  <div class="modal-footer">
                		  <button type="button" id="updateTestCloseBtn" class="btn btn-default" data-dismiss="modal">关闭</button>
                		  <button id="addAll" type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
            		</div>
       		 	</div><!-- /.modal-content -->
   			</div><!-- /.modal -->
		</div>
		
		<!--添加试题 模态框（Modal） -->
		<div class="modal fade" id="addStudentModal" tabindex="-1" role="dialog" aria-labelledby="addTestModalLabel" aria-hidden="true">
  			  <div class="modal-dialog">
   			     <div class="modal-content">
         			   <div class="modal-header">
             				   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
             				   <h4 class="modal-title" id="myModalLabel">添加学员</h4>
          			  </div>
           			  <div class="modal-body">
           			  	<form id="addTestForm" method="POST">
           			  		<p><label>学号:</label><input type="text" name="userId" placeholder="填写正确的学号" /></p>
           			  		<p><label>名字:</label><input type="text" name="userName" placeholder="填写正确的名字" /></p>
           			  		<p><label>性别：</label><input type="text" name="gender" placeholder="填写正确的性别 "/></p>
           			  		<p><label>电话:</label><input type="text" name="tel" placeholder="填写正确的电话" /></p>
           			  		<p><label>邮箱:</label><input type="text" name="email" placeholder="填写正确的邮箱" /></p>
           			  		<p><label>地址:</label><input type="text" name="address" placeholder="填写正确的地址" /></p>
           			  		<p><label>生日:</label><input type="text" name="birthday" placeholder="填写正确的生日" /></p>
           			  	</form>
           			  </div>
            		  <div class="modal-footer">
                		  <button type="button" id="updateTestCloseBtn" class="btn btn-default" data-dismiss="modal">关闭</button>
                		  <button id="addSaveBtn" type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
            		</div>
       		 	</div><!-- /.modal-content -->
   			</div><!-- /.modal -->
		</div>
		
		<!--修改试题 模态框（Modal） -->
		<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateTestModalLabel" aria-hidden="true">
  			  <div class="modal-dialog">
   			     <div class="modal-content">
         			   <div class="modal-header">
         			   		   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
             				   <h4 class="modal-title" id="myModalLabel">修改学员</h4>
          			  </div>
           			  <div class="modal-body">
           			  	<form id="updateForm" method="POST">
           			  		<p><label>学号:</label><input type="text" name="userId" placeholder="填写正确的学号" /></p>
           			  		<p><label>名字:</label><input type="text" name="userName" placeholder="填写正确的名字" /></p>
           			  		<p><label>性别：</label><input type="text" name="gender" placeholder="填写正确的性别" /></p>
           			  		<p><label>电话:</label><input type="text" name="tel" placeholder="填写正确的电话" /></p>
           			  		<p><label>邮箱:</label><input type="text" name="email" placeholder="填写正确的邮箱" /></p>
           			  		<p><label>地址:</label><input type="text" name="address" placeholder="填写正确的地址" /></p>
           			  		<p><label>生日:</label><input type="text" name="birthday" placeholder="填写正确的生日" /></p>
           			  	</form>
           			  </div>
            		  <div class="modal-footer">
                		  <button id="updateTestCloseBtn" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                		  <button id="updateSaveBtn" type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
            		</div>
       		 	</div><!-- /.modal-content -->
   			</div><!-- /.modal -->
		</div>
		
		
		<input type="hidden" id="testId" value="testId"> 
	</body>
</html>
