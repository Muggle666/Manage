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
<script src="${pageContext.request.contextPath}/static/lib/jquery-migrate-1.1.0.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/jquery.jqprint-0.3.js"></script>
		<script>
			$(function(){
				$('.test').each(function(){
					 $(this).click(function(){
			 			  if(confirm("确定要进入考试吗？")){
				 			  
			 			  }else{
			 				  $(this).attr("href","");
			 			  }
			  		  })
					});
				
			/* 点击按钮后。在modal里面更新数据 */
			$('.showScoreBtn').click(function(){
				var scoreId = $(this).attr('scoreId');
				$.ajax({
					type: 'POST',
					url: '${contextPath}/Manage/student/showScore',
					data: {scoreId: scoreId},
					dataType: "json",
					success: function(data){
						$('#userName').text(data.userName);
						$('#name').text(data.name);
						$('#examTime').text(data.examTimeString);
						$('#score').text(data.score);
						$('#showScoreModal').modal('show');
						}
					});
				});
					/* 使用jqprint打印modal框内的内容 */
					$('#scorePrint').click(function(){
						$('#showScoreModal .modal-body').jqprint();
					});

					/* 点击进入考试 */
					/* $('.test').click(function(){
						var subId = $(this).attr('subId');
						$.ajax({
							type: 'GET',
							url: '${contextPath}/Manage/student/toTest',
							data: {subId: subId},
							dataType: "JSON",
							success: function(data){
								alert("Hello");
								}
							});
						}); */

					
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
								<!-- PAGE CONTENT BEGINS -->
								<div class="row">
									<div class="col-xs-12">
										<table id="sample-table-1" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>科目编号</th>
													<th>科目名</th>
													<th>科目描述</th>
													<th>总科目题数</th>
													<th>科目总时长</th>
													<th>科目标准总分</th>
													<th>是否开启考试</th>
													<th>考试总得分</th>
													<th>是否已参考</th>
													<th>操作</th>
												</tr>
											</thead>

											<tbody>
											
	 <c:forEach items="${subjectList}" var="sub">
        <tr>
       
            <td>${sub.subId}</td>
            <td>${sub.name}</td>
            <td>${sub.description}</td>
            <td>${sub.testCount}</td>
            <td>${sub.totalTime}</td>
            <td>${sub.totalScore}</td>
            
            
            <td>
            <%--if () else if () else if ()     else  --%>
		            <c:choose>
			            <c:when test="${sub.status == 0 }">
			            	<label class="label arrowed">已关闭</label>
			            </c:when>
			            <c:otherwise>
			            	<label class="label label-success arrowed-in arrowed-in-right">已开启</label>
			            </c:otherwise>
			            </c:choose>
			</td>
			            
			<td>
			    ${sub.examScoreVO.score }
			</td>
			            
		 	<td>
			      <c:choose>
			            <c:when test="${sub.examScoreVO.score == null }">
			            	<label class="label label-success arrowed-in arrowed-in-right">未参考</label>
			            </c:when>
			            <c:otherwise>
			            	<label class="label arrowed">已参考</label>
			            </c:otherwise>
		         </c:choose>
            </td>
            
            <td>
	            <c:choose>
		            <c:when test="${sub.examScoreVO.score == null }">
			            <c:choose>
				            <c:when test="${sub.status == 0 }">
					            <a title="进入考试" disabled="disabled" class="btn btn-success btn-xs">
					            <i class="ace-icon fa fa-pencil"></i>不可进入考试</a>
			           		</c:when>
			            
				            <c:otherwise>
				             <%-- href="${contextPath }/student/toTest/${sub.subId}" --%>
					            <a title="进  入  考  试" subId="${sub.subId}" href="${contextPath}/Manage/student/toTest/${sub.subId}" class="btn btn-success btn-xs test">
					            <i class="ace-icon fa fa-pencil"></i>可进入考试</a>
				            </c:otherwise>
			            </c:choose>
		            </c:when>
		            
		            <c:when test="${empty sub.examScoreVO.score && sub.examScoreVO.remainingExamTime > 0}">
		            <!--已经开始考试，中途关闭-->
		           <%--  href="${contextPath }/student/toTest/${sub.subId }"  --%>
			            <a title="进入考试" subId="${sub.subId}" href="${contextPath}/Manage/student/toTest/${sub.subId}" class="btn btn-success btn-xs test">
			            <i class="ace-icon fa fa-pencil"></i>
			            </a>
		            </c:when>
		            	<c:otherwise>
				            <a title="查看成绩" scoreId="${sub.examScoreVO.id}" class="btn btn-info btn-xs showScoreBtn">
				            <i class="ace-icon fa fa-eye"></i>查看成绩</a>
		           		</c:otherwise>
	            </c:choose>
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

				<!--显示成绩－模态框（Modal）-->
				<div class="modal fade" id="showScoreModal" tabindex="-1"
					role="dialog" aria-labelledby="showScoreModalLabel"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="showScoreModalLabel">查看科目成绩</h4>
							</div>
							<div class="modal-body">
								<p>
									<label>用户名:</label><label id="userName">andy</label>
								</p>
								<p>
									<label>科目名:</label><label id="name">English</label>
								</p>
								<p>
									<label>考试时间:</label><label id="examTime">2016-10-18</label>
								</p>
								<p>
									<label>得分:</label><label id="score">80</label>
								</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
								<button id="scorePrint" type="button" class="btn btn-primary">打印</button>
							</div>
						</div>
						<!--/.modal-content -->
					</div>
					<!--/.modal -->
				</div>
		<input type="hidden" id="testId" value="testId"> 
	</body>
</html>
