<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery-migrate-1.1.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/jquery.jqprint-0.3.js"></script>
		<title>科目列表</title>
		
		<style type="text/css">
			ul li{
				list-style-type:none;
			}
			
			.u-learn-modulewidth {
			    width: 80%;
			    background-color: white;
			    padding: 20px;
			    margin:auto;
			    min-height: 100%;
			}
			
			.u-learn-moduletitle {
			    padding: 10px 0;
			    margin-bottom: 20px;
			    border-bottom: 1px solid #eee;
			    text-align: center;
			}
			
			.m-quizScore .totalScore {
			    margin: 20px 0 0;
			    padding: 10px 20px;
			    background-color: #feffd1;
			    font-size: 15px;
			    color: #666;
			}
			
			.m-quizScore .testDescription {
			    margin: 20px 0 0;
			    padding: 10px 20px;
			    background-color: #feffd1;
			    font-size: 15px;
			    color: #666;
			}
			.f-f0 {
			    font-family: "Arial","Hiragino Sans GB","Microsoft YaHei", \5fae\8f6f\96c5\9ed1, "Helvetica", "sans-serif";
			}
			.u-questionItem {
			    border-bottom: 1px dashed #d9d9d9;
			    background-color: #fff;
			    padding: 18px 0 5px;
			    font-size: 14px;
			}
			.type-mark{
			    font-size: 12px;
			    background: #E3F8FF;
			    border: 1px solid #BFDFFF;
			    border-radius: 2px;
			    padding: 2px 4px;
			    margin-right: 5px;
			    max-height: 24px;
			    min-width: 36px;
			}
			.question-score{
				min-width: 40px;
			}
			.test-title{
			    color: #333;
			    font-size: 14px;
			    margin: 0 0 10px;
			    display: flex;
			    }
			.test-title .title-content{
			    line-height: 30px;
			    padding: 5px;
			    margin-top: -9px;
			    }
			.option-item{
				margin-left:16px;
			
			}
			.option{
				margin: 0 0 0px;
				padding: 8px;
				cursor: pointer;
				display: flex;
			}
			.option span.option-content{
				line-height: 30px;
				margin-top: -5px;
			}
			.fill-blank-area{
				width:280px;
				margin:10px 10px 10px 20px;
			}
			.tips{
				color: rgba(84, 64, 64, 0.71);
			    background-color: rgba(239, 11, 11, 0.13);
			    border-radius: 2px;
			    padding: 3px;
			    margin-bottom: 5px;
			    margin-left: 20px;
			    margin-top: -2px;
			}
			.is-right-mark{
				color: rgba(24, 195, 24, 0.85);
			    margin: 0px 40px 0px 20px;
			    font: icon;
			}
			.is-wrong-mark{
				color: red;
			    margin: 0px 40px 0px 20px;
			    font: icon;
			}
			textarea{ 
				resize:none;
			 	border-radius: 3px;
			 	}
			.btn-submit{
				display: inline-block;
			    padding: 4px 9px;
			    font-size: 14px;
			    line-height: 28px;
			    cursor: pointer;
			}
			.message{
				margin: 20px 0 40px;
			    padding: 10px 20px;
			    background-color: #feffd1;
			    font-size: 15px;
			    color: #ff8080;
			}
			input[type="radio"],input[type="checkbox"]{
				cursor: pointer;
				margin: 4px 6px 0px 0px;
			}
		</style>
		
		<script>
			$(function(){
				
				/* 鼠标进入离开选项样式控制 */
				$(document).on('mouseenter', '.option', function(){
					$(this).css("background-color","#CCFFCC");
			    })
			    $(document).on('mouseleave', '.option', function(){
			    	var ischecked=$(this).children().children("input.u-tbi").attr("ischecked");
			    	var isChecked=$(this).children().children("input.u-tbi").prop("checked");  //鼠标离开前选项是否已选中
			    	if(isChecked==false){
			    		$(this).css("background-color","#ffffff");     //未选中则背景改为白色
			    	} else{
			    		$(this).css("background-color","#f8f8f8");
			    	} 
					
			    }) 
			   /*  选项选中样式控制 */
			    $(document).on('click', '.option', function(){
			    	$(this).css("background-color","#f8f8f8");			//选中样式
			    	var type= $(this).parents("div.u-questionItem").attr("q_type");  //题目类型
			    	if(type==2){		//若题目类型为多选题
			    		if($(this).children().children("input.u-tbi").prop("checked")){
			    			$(this).children().children("input.u-tbi").removeAttr("checked");
			    		}else{
			    			$(this).children().children("input.u-tbi").prop("checked",true);
			    		}
			    	}
			    	if(type!=2){	//若题目类型为其他（单选，判断，填空）
			    		$(this).children().children("input.u-tbi").prop("checked",true);
			    		$(this).siblings().each(function(){
			    			$(this).css("background-color","#ffffff");
			    			$(this).children().children("input.u-tbi").removeAttr("checked");
			    		})
			    	}
			    })
			    
			    	/* 解决checkbox单击未选中问题，问题原因：执行选中事件后触发了li的mouseleave事件 */
			     $(document).on('click', ':checkbox.u-tbi', function(){
			    	 checked = $(this).prop("checked");
			    	 if(checked){
			    		 checked = $(this).prop("checked",false);
			    	 }else{
			    		 checked = $(this).prop("checked",true);
			    	 }
			    	 
			     })
				
				/* function initTimer(){
					var resultTime = 0;
					var testTotalTime = Number('${remainingTime}');
					return testTotalTime;
				} */
				
				function startTimer(totalSecond){
					$('#testTimer').text(formatTime(totalSecond));
					var timer = setInterval(function(){
						totalSecond --;
						if(totalSecond >= 0){
							$('#testTimer').text(formatTime(totalSecond));
							//更新COOKIE
						}else{
							alert("考试结束");
							clearInterval(timer);
							assignment();
						}
					}, 1000);
				}
				
				function formatTime(totalSecond){
					var hour = Math.floor(totalSecond / 3600);
					var min = Math.floor((totalSecond % 3600) / 60);
					var second = (totalSecond % 60);
					hour = formatTimeNumber(hour);
					min = formatTimeNumber(min);
					second = formatTimeNumber(second);
					return hour + ":" + min + ":" +second;
				}
				
				function formatTimeNumber(timeNumber){
					if(timeNumber < 10){
						return "0" + timeNumber;
					}else{
						return timeNumber;
					}
				}
				
				function assignment(){
					$("#test-form").submit();
				}
				
				$("#answer_submit").click(function(){
			 		if(confirm("确定提交答案吗？")){
			 			assignment();
			 		}else{
			 			return false;
			 		}
				});
				
				startTimer("${subject.examScoreVO.remainingExamTime}");
				
			});
		</script>
	</head>

	<body class="no-skin">
		<div id="content" class="m-quizScore u-learn-modulewidth">
		<div class="u-learn-moduletitle f-cb"><h2 id="title">【考核测验】${subject.name }</h2></div>
		<!-- 测验说明 -->
		<div class="testDescription">本次考试的参考时长为：<b>${subject.totalTime }</b> 分钟 &nbsp;  总分为：<b>${subject.totalScore }</b> 分  &nbsp;  剩余时间时间为：<b id="testTimer">${subject.examScoreVO.remainingExamTime}</b>  </div> 
		<div id="showQuestions" style="margin-left:20px">
		
		<form id="test-form" action="${pageContext.request.contextPath}/student/assignment" method="post">
			<c:forEach items="${testList }" var="testTypeList" varStatus="status0">
				<c:choose>
					<c:when test="${testTypeList.key == 1 }">
						<div class="s-choiceQuestion">
							<c:forEach items="${testTypeList.value}" var="test" varStatus="status1">
								<div class="u-questionItem" q_type="${testTypeList.key}">
								<div class="test-title">  ${status1.index + 1}、<span class="type-mark">${test.examTestTypeVO.testType }</span> <span class="question-score">(${test.examTestTypeVO.testTypeScore }分)</span> <span class="title-content">${test.content }</span></div>
								<div class="option-item"> <ul>
									<li class="option" style="background-color: rgb(255, 255, 255);"><label><input name="test_${test.id}" value="A" class="u-tbi" type="radio"> A、<span class="option-content">${test.chooseA }</span></label></li>
									<li class="option" style="background-color: rgb(255, 255, 255);"><label><input name="test_${test.id}" value="B" class="u-tbi" type="radio"> B、<span class="option-content">${test.chooseB }</span></label></li>
									<li class="option" style="background-color: rgb(248, 248, 248);"><label><input name="test_${test.id}" value="C" class="u-tbi" type="radio"> C、<span class="option-content">${test.chooseC }</span></label></li>
									<li class="option" style="background-color: rgb(255, 255, 255);"><label><input name="test_${test.id}" value="D" class="u-tbi" type="radio"> D、<span class="option-content">${test.chooseD }</span></label></li>
									</ul></div></div>
							</c:forEach>
						</div>
					</c:when>
					<c:otherwise>
						<div class="m-choiceQuestion">
							<c:forEach items="${testTypeList.value}" var="test" varStatus="status1">
								<div class="u-questionItem" q_type="${testTypeList.key}">
								<div class="test-title">  ${status1.index + 1}、<span class="type-mark">${test.examTestTypeVO.testType }</span> <span class="question-score">(${test.examTestTypeVO.testTypeScore }分)</span> <span class="title-content">${test.content }</span></div>
								<div class="option-item"> <ul>
									<li class="option" style="background-color: rgb(255, 255, 255);"><label><input name="test_${test.id}" value="A" class="u-tbi" type="checkbox"> A、<span class="option-content">${test.chooseA }</span></label></li>
									<li class="option" style="background-color: rgb(255, 255, 255);"><label><input name="test_${test.id}" value="B" class="u-tbi" type="checkbox"> B、<span class="option-content">${test.chooseB }</span></label></li>
									<li class="option" style="background-color: rgb(248, 248, 248);"><label><input name="test_${test.id}" value="C" class="u-tbi" type="checkbox"> C、<span class="option-content">${test.chooseC }</span></label></li>
									<li class="option" style="background-color: rgb(255, 255, 255);"><label><input name="test_${test.id}" value="D" class="u-tbi" type="checkbox"> D、<span class="option-content">${test.chooseD }</span></label></li>
									</ul></div></div>
							</c:forEach>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<input name="subId" type="hidden" value="${subject.subId }" />
			<div style="margin-top:20px; margin-left:40px;margin-bottom: 40px"><button class="btn btn-normal btn-success" id="answer_submit" style="margin-right:30px">提交</button><button class="btn btn-normal btn-fail" type="button" onclick="javascript:window.history.go(-1);">返回</button></div>
		</form>
		
			<div id="messageBox" class="message" style="display: none;"><b>消息提示：</b><span id="mess_content"></span></div>
		</div><!-- showQuestions-end -->
	</div> <!-- content-end -->

	</body>
</html>
