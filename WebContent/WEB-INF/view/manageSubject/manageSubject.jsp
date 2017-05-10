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
				$(".deleteSubject").each(function(){
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
				$('#addSubjectBtn').click(function(){
					$('#addSubjectModal').modal('show');
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
				//添加单条信息
				$("#addSaveBtn").click(function(){
					
					if(true){
						var subId = $("#addTestForm input[name='subId']").val();
						var name = $("#addTestForm input[name='name']").val();
						var description = $("#addTestForm input[name='description']").val();
						var testCount = $("#addTestForm input[name='testCount']").val();
						var totalTime = $("#addTestForm input[name='totalTime']").val();
						var totalScore = $("#addTestForm input[name='totalScore']").val();
						/* var status = $("#addTestForm input[name='status']").val(); */
						var status = $("#addTestForm input[name='status']").val();
						$.ajax({
							type: 'POST',
							url: '${contextPath}/Manage/save',
							data: {
								subId: subId,
								name: name,
								description: description,
								testCount: testCount,
								totalTime: totalTime,
								totalScore: totalScore,
								status : status,
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
					$(".editSubject").click(function(){
					 var subId = $(this).parent().siblings().eq(1).text();
					 var name = $(this).parent().siblings().eq(2).text();
				     var description = $(this).parent().siblings().eq(3).text();
					 var testCount = $(this).parent().siblings().eq(4).text();
					 var totalTime = $(this).parent().siblings().eq(5).text();
					 var totalScore = $(this).parent().siblings().eq(6).text();
					 var status = $(this).parent().siblings().eq(7).text();
					 $("#updateForm input[name='subId']").val(subId);
						$("#updateForm input[name='name']").val(name);
						$("#updateForm input[name='description']").val(description)
						$("#updateForm input[name='testCount']").val(testCount);
						$("#updateForm input[name='totalTime']").val(totalTime);
						$("#updateForm input[name='totalScore']").val(totalScore);
						$("#updateForm input[name='status']").val(status);
				}); 
				
				//修改ajax
				$("#updateSaveBtn").click(function(){
					if(true){
						var subId = $("#updateForm input[name='subId']").val();
						var name = $("#updateForm input[name='name']").val();
						var description= $("#updateForm input[name='description']").val();
						var testCount= $("#updateForm input[name='testCount']").val();
						var totalTime= $("#updateForm input[name='totalTime']").val();
						var totalScore= $("#updateForm input[name='totalScore']").val();
						var status= $("#updateForm input[name='status']").val();
						$.ajax({
							type: 'POST',
							url: '${contextPath}/Manage/edit',
							data: {
								subId: subId,
								name: name,
								description: description,
								testCount: testCount,
								totalTime: totalTime,
								totalScore: totalScore,
								status: status
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
			//点击管理学生科目按钮，读取所有学生姓名和学号
				$('.manage-subject-student-btn').click(function() {
									var subId = $(this).parents("td").siblings("td").eq(1).text();
									$.ajax({
												type : 'post',
												url : '${contextPath}/Manage/getAllStudent',
												data : {
													subId : subId
												},
												dataType : "json",
												success : function(data) {
													var htmlString = '';
													var selectedUser = data.selectedUser;
													for (var i = 0; i < data.allUser.length; i++) {
														var user = data.allUser[i];
														var checked = checkSelectedUser(selectedUser, user.userId) ? 'checked="checked" ': '';
														htmlString += '<div class="checkbox">';
														htmlString += '<label>';
														htmlString += '<input name="subjectStudents" class="ace ace-checkbox-2 checkBox" type="checkbox" value="'
								+ user.userId + '"' + checked + ' />';
														htmlString += '<span class="lbl">'
																+ user.userName
																+ '</span>';
														htmlString += '</label>';
														htmlString += '</div>';
													}
													$('#subjectStudentContent')
															.html(htmlString);
													$('#subId').val(subId);
												}
											});
									$('#modalSubjctName').text(
											$(this).parents("td")
													.siblings("td").eq(1)
													.text());
									$('#subjectStudentModal').modal('show');
								});
							function checkSelectedUser(array,str){
								for(var i = 0 ;i < array.length ; i++){
									if(array[i] == str){
										return true;
									}
								}
								return false;
							}
							//是否开启考试
							$('.subject-status').click(function(){
								var status = $(this).val();
								var subId = $(this).parents("td").siblings("td").eq(1).text();
								$.ajax({
									type : 'post', 
									url : '${contextPath}/Manage/changeStatus',
									data : {
										status : status,
										subId : subId
									},
									dataType : "json",
									success : function(data) {
										if (data.success) {
											alert('成功');
										} else {
											alert('失敗');
										}
									}
								});
							});
							
				$('#subjectStudentSaveBtn').on('click',function() {
									var subjectStudents = []; 
									//獲取所有當前已經勾選的學生的學號
									for (var i = 0; i < $('input[name="subjectStudents"]:checked').length; i++) {
										subjectStudents[i] = $($('input[name="subjectStudents"]:checked')[i]).val();
									}
									$.ajax({
												type : 'post', 
												url : '${contextPath}/Manage/saveSubjectStudents',
												data : {
													subjectStudents : subjectStudents,//把所有勾選上的學號以數組的形式闖到後台
													subId : $('#subId').val()
												},
												dataType : "json",
												success : function(data) {
													if (data.success) {
														alert('成功');
														$('#subjectStudentModal').modal('hide');
													} else {
														alert('失敗');
													}
												}
											});
								})
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
										<button id="addSubjectBtn" class="btn btn-primary">
									<i class="ace-icon fa fa-plus align-top bigger-125"></i>
									添加科目信息
									</button>
									<button id="batchAddStudentBtn" class="btn btn-primary">
									<i class="ace-icon fa fa-plus align-top bigger-125"></i>
									批量添加科目信息
									</button>
									<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="row">
									<div class="col-xs-12">
										<table id="sample-table-1" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="center">
														<label class="position-relative">
															<input type="checkbox" class="ace" />
															<span class="lbl"></span>
														</label>
													</th>
													<th>SubjectId</th>
													<th>Name</th>
													<th>description</th>
													<th>testCount</th>
													<th>totalTime</th>
													<th>totalScore</th>
													<th>Open The Exam</th>
													<th>Action</th>
													
												</tr>
											</thead>

											<tbody>
											
	 <c:forEach items="${subjectList}" var="sub">
        <tr>
        <td class="center">
		<label class="position-relative">
		<input type="checkbox" class="ace" />
		<span class="lbl"></span>
		</label>
		</td>
            <td>${sub.subId}</td>
            <td>${sub.name}</td>
            <td>${sub.description}</td>
            <td>${sub.testCount}</td>
            <td>${sub.totalTime}</td>
            <td>${sub.totalScore}</td>
            
            <td>
            <c:choose>
            <c:when test="${sub.status == 0 }">
            <input name="status" value="0" subId="${sub.subId }" class="ace ace-switch ace-switch-6 subject-status" type="checkbox">
            <span class="lbl"></span>
            </c:when>
            <c:otherwise>
            <input name="status" value="1" subId="${sub.subId }" checked class="ace ace-switch ace-switch-6 subject-status" type="checkbox">
            <span class="lbl"></span>
            </c:otherwise>
            </c:choose>
            </td>
            <td>
            <a title="修改科目" class="btn btn-xs btn-primary editSubject" data-toggle="modal" data-target="#updateSubjectModal">
			<i class="ace-icon fa fa-pencil"></i></a>
			<a title="删除科目" class="btn btn-xs btn-primary deleteSubject" href="delete/${sub.subId}">
			<i class="ace-icon fa fa-trash-o"></i></a>
			<a title="管理科目考生" class="btn btn-xs btn-primary manage-subject-student-btn">
			<i class="glyphicon glyphicon-user"></i></a>
            </td>
        </tr>
    </c:forEach>
											</tbody>
										</table>
									</div><!-- /.span -->
								</div><!-- /.row -->
								</div>

								<div id="modal-table" class="modal fade" tabindex="-1">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header no-padding">
												<div class="table-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														<span class="white">&times;</span>
													</button>
													Results for "Latest Registered Domains
												</div>
											</div>
										</div><!-- /.modal-content -->
									</div><!-- /.modal-dialog -->
								</div><!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${pageContext.request.contextPath}/static/assets/js/jquery.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${pageContext.request.contextPath}/static/assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/static/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		

		<!-- page specific plugin scripts -->
		<script src="${pageContext.request.contextPath}/static/assets/js/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/static/assets/js/jquery.dataTables.bootstrap.js"></script>

		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/static/assets/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/static/assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
				var oTable1 = 
				$('#sample-table-2')
				//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
				.dataTable( {
					bAutoWidth: false,
					"aoColumns": [
					  { "bSortable": false },
					  null, null,null, null, null,
					  { "bSortable": false }
					],
					"aaSorting": [],
			
			    } );
			
				$(document).on('click', 'th input:checkbox' , function(){
					var that = this;
					$(this).closest('table').find('tr > td:first-child input:checkbox')
					.each(function(){
						this.checked = that.checked;
						$(this).closest('tr').toggleClass('selected');
					});
				});
			
			
				$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('table')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					//var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
			
			})
		</script>

		<!-- the following scripts are used in demo only for onpage help and you don't need them -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/ace.onpage-help.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/docs/assets/js/themes/sunburst.css" />

		<script type="text/javascript"> ace.vars['base'] = '..'; </script>
		<script src="${pageContext.request.contextPath}/static/assets/js/ace/elements.onpage-help.js"></script>
		<script src="${pageContext.request.contextPath}/static/assets/js/ace/ace.onpage-help.js"></script>
		<script src="${pageContext.request.contextPath}/static/docs/assets/js/rainbow.js"></script>
		<script src="${pageContext.request.contextPath}/static/docs/assets/js/language/generic.js"></script>
		<script src="${pageContext.request.contextPath}/static/docs/assets/js/language/html.js"></script>
		<script src="${pageContext.request.contextPath}/static/docs/assets/js/language/css.js"></script>
		<script src="${pageContext.request.contextPath}/static/docs/assets/js/language/javascript.js"></script>
		
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
		<div class="modal fade" id="addSubjectModal" tabindex="-1" role="dialog" aria-labelledby="addTestModalLabel" aria-hidden="true">
  			  <div class="modal-dialog">
   			     <div class="modal-content">
         			   <div class="modal-header">
             				   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
             				   <h4 class="modal-title" id="myModalLabel">添加科目</h4>
          			  </div>
           			  <div class="modal-body">
           			  	<form id="addTestForm" method="POST">
           			  		<p><label>科目编号:</label><input type="text" name="subId" placeholder="填写正确的科目编号" /></p>
           			  		<p><label>科目名:</label><input type="text" name="name" placeholder="填写正确的科目名" /></p>
           			  		<p><label>科目描述：</label><input type="text" name="description" placeholder="填写正确的科目描述 "/></p>
           			  		<p><label>科目题数:</label><input type="text" name="testCount" placeholder="填写正确的科目题数" /></p>
           			  		<p><label>总时长:</label><input type="text" name="totalTime" placeholder="填写正确的总时长" /></p>
           			  		<p><label>总分:</label><input type="text" name="totalScore" placeholder="填写正确的总分" /></p>
           			  	<p><label>是否开启考试：</label><input type="text" name="status" placeholder="开启为1，关闭未0" /></p>
   
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
		<div class="modal fade" id="updateSubjectModal" tabindex="-1" role="dialog" aria-labelledby="updateTestModalLabel" aria-hidden="true">
  			  <div class="modal-dialog">
   			     <div class="modal-content">
         			   <div class="modal-header">
         			   		   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
             				   <h4 class="modal-title" id="myModalLabel">修改学员</h4>
          			  </div>
           			  <div class="modal-body">
           			  	<form id="updateForm" method="POST">
           			  		<p><label>科目编号:</label><input type="text" name="subId" placeholder="填写正确的科目编号" /></p>
           			  		<p><label>科目名:</label><input type="text" name="name" placeholder="填写正确的科目名" /></p>
           			  		<p><label>科目描述：</label><input type="text" name="description" placeholder="填写正确的科目描述 "/></p>
           			  		<p><label>科目题数:</label><input type="text" name="testCount" placeholder="填写正确的科目题数" /></p>
           			  		<p><label>总时长:</label><input type="text" name="totalTime" placeholder="填写正确的总时长" /></p>
           			  		<p><label>总分:</label><input type="text" name="totalScore" placeholder="填写正确的总分" /></p>
           			  		<p><label>是否开启考试：</label><input type="text" name="status" placeholder="开启为1，关闭未0" /></p>
           			  	</form>
           			  </div>
            		  <div class="modal-footer">
                		  <button id="updateTestCloseBtn" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                		  <button id="updateSaveBtn" type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
            		</div>
       		 	</div><!-- /.modal-content -->
   			</div><!-- /.modal -->
		</div>
		
		<!--管理学生 模态框（Modal） -->
		<div class="modal fade" id="subjectStudentModal" tabindex="-1" role="dialog" aria-labelledby="subjectStudentModal" aria-hidden="true">
  			  <div class="modal-dialog">
   			     <div class="modal-content">
         			   <div class="modal-header">
             				   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
             				   <h4 class="modal-title" id="subjectStudentModalLable">管理学生科目:<label id="modalSubjectName"></label></h4>
          			  </div>
          			  
           			  <div class="modal-body">
           			  <input type="hidden" id="subId" />
           			  <div id="subjectStudentContent" class="control-group">
           			  
           			  </div>
           			  </div>
            		  <div class="modal-footer">
                		  <button type="button" id="updateTestCloseBtn" class="btn btn-default" data-dismiss="modal">关闭</button>
                		  <button id="subjectStudentSaveBtn" type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
            		</div>
       		 	</div><!-- /.modal-content -->
   			</div><!-- /.modal -->
		</div>
		<input type="hidden" id="testId" value="testId"> 
	</body>
</html>
