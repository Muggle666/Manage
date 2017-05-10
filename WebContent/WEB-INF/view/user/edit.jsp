<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>修改用户</title>
    <script src="${pageContext.request.contextPath}/static/lib/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/lib/jquery.metadata.js"></script>
    <script src="${pageContext.request.contextPath}/static/lib/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/lib/messages_zh.js"></script>

    <meta name="description" content="Common form elements and layouts" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/font-awesome.min.css" />

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/jquery-ui.custom.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/chosen.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/datepicker.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/bootstrap-timepicker.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/daterangepicker.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/bootstrap-datetimepicker.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/assets/css/colorpicker.css" />

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
    <link rel="stylesheet" href="../assets/css/ace-ie.min.css" />
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="${pageContext.request.contextPath}/static/assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="${pageContext.request.contextPath}/static/assets/js/html5shiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/assets/js/respond.min.js"></script>
    <![endif]-->

    <!-- jquery validate -->
    <script type="text/javascript">
        $(document).ready(function() {
            $("#userForm").validate();
        });
    </script>
</head>

<body class="no-skin">
<!-- #section:basics/navbar.layout -->
<div id="navbar" class="navbar navbar-default">
    <script type="text/javascript">
        try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>

    <div class="navbar-container" id="navbar-container">
        <!-- #section:basics/sidebar.mobile.toggle -->
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <!-- /section:basics/sidebar.mobile.toggle -->
        <div class="navbar-header pull-left">
            <!-- #section:basics/navbar.layout.brand -->
            <a href="#" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    Ace Admin
                </small>
            </a>
        </div>
    </div><!-- /.navbar-container -->
</div>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <!-- #section:basics/sidebar -->
    <div id="sidebar" class="sidebar                  responsive">
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
        </script>

        <div class="sidebar-shortcuts" id="sidebar-shortcuts">
            <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                <button class="btn btn-success">
                    <i class="ace-icon fa fa-signal"></i>
                </button>

                <button class="btn btn-info">
                    <i class="ace-icon fa fa-pencil"></i>
                </button>

                <!-- #section:basics/sidebar.layout.shortcuts -->
                <button class="btn btn-warning">
                    <i class="ace-icon fa fa-users"></i>
                </button>

                <button class="btn btn-danger">
                    <i class="ace-icon fa fa-cogs"></i>
                </button>

                <!-- /section:basics/sidebar.layout.shortcuts -->
            </div>

            <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                <span class="btn btn-success"></span>

                <span class="btn btn-info"></span>

                <span class="btn btn-warning"></span>

                <span class="btn btn-danger"></span>
            </div>
        </div><!-- /.sidebar-shortcuts -->

        <ul class="nav nav-list">
            <li class="">
                <a href="index.html">
                    <i class="menu-icon fa fa-tachometer"></i>
                    <span class="menu-text"> Dashboard </span>
                </a>

                <b class="arrow"></b>
            </li>

            <li class="">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-desktop"></i>
                    <span class="menu-text"> UI &amp; Elements </span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-caret-right"></i>

                            Layouts
                            <b class="arrow fa fa-angle-down"></b>
                        </a>

                        <b class="arrow"></b>

                        <ul class="submenu">
                            <li class="">
                                <a href="top-menu.html">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    Top Menu
                                </a>

                                <b class="arrow"></b>
                            </li>

                            <li class="">
                                <a href="mobile-menu-1.html">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    Default Mobile Menu
                                </a>

                                <b class="arrow"></b>
                            </li>

                            <li class="">
                                <a href="mobile-menu-2.html">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    Mobile Menu 2
                                </a>

                                <b class="arrow"></b>
                            </li>

                            <li class="">
                                <a href="mobile-menu-3.html">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    Mobile Menu 3
                                </a>

                                <b class="arrow"></b>
                            </li>
                        </ul>
                    </li>

                    <li class="">
                        <a href="typography.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Typography
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="elements.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Elements
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="buttons.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Buttons &amp; Icons
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="treeview.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Treeview
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="jquery-ui.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            jQuery UI
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="nestable-list.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Nestable Lists
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-caret-right"></i>

                            Three Level Menu
                            <b class="arrow fa fa-angle-down"></b>
                        </a>

                        <b class="arrow"></b>

                        <ul class="submenu">
                            <li class="">
                                <a href="#">
                                    <i class="menu-icon fa fa-leaf green"></i>
                                    Item #1
                                </a>

                                <b class="arrow"></b>
                            </li>

                            <li class="">
                                <a href="#" class="dropdown-toggle">
                                    <i class="menu-icon fa fa-pencil orange"></i>

                                    4th level
                                    <b class="arrow fa fa-angle-down"></b>
                                </a>

                                <b class="arrow"></b>

                                <ul class="submenu">
                                    <li class="">
                                        <a href="#">
                                            <i class="menu-icon fa fa-plus purple"></i>
                                            Add Product
                                        </a>

                                        <b class="arrow"></b>
                                    </li>

                                    <li class="">
                                        <a href="#">
                                            <i class="menu-icon fa fa-eye pink"></i>
                                            View Products
                                        </a>

                                        <b class="arrow"></b>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>


            <li class="active open">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-pencil-square-o"></i>
                    <span class="menu-text"> Forms </span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="active">
                        <a href="form-elements.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Form Elements
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="form-wizard.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Wizard &amp; Validation
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="wysiwyg.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Wysiwyg &amp; Markdown
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="dropzone.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Dropzone File Upload
                        </a>

                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>

            <li class="">
                <a href="widgets.html">
                    <i class="menu-icon fa fa-list-alt"></i>
                    <span class="menu-text"> Widgets </span>
                </a>

                <b class="arrow"></b>
            </li>

            <li class="">
                <a href="calendar.html">
                    <i class="menu-icon fa fa-calendar"></i>

							<span class="menu-text">
								Calendar

                                <!-- #section:basics/sidebar.layout.badge -->
								<span class="badge badge-transparent tooltip-error" title="2 Important Events">
									<i class="ace-icon fa fa-exclamation-triangle red bigger-130"></i>
								</span>

                                <!-- /section:basics/sidebar.layout.badge -->
							</span>
                </a>

                <b class="arrow"></b>
            </li>

            <li class="">
                <a href="gallery.html">
                    <i class="menu-icon fa fa-picture-o"></i>
                    <span class="menu-text"> Gallery </span>
                </a>

                <b class="arrow"></b>
            </li>

            <li class="">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-tag"></i>
                    <span class="menu-text"> More Pages </span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="">
                        <a href="profile.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            User Profile
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="inbox.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Inbox
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="pricing.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Pricing Tables
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="invoice.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Invoice
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="timeline.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Timeline
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="email.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Email Templates
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="login.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Login &amp; Register
                        </a>

                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>

            <li class="">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-file-o"></i>

							<span class="menu-text">
								Other Pages

                                <!-- #section:basics/sidebar.layout.badge -->
								<span class="badge badge-primary">5</span>

                                <!-- /section:basics/sidebar.layout.badge -->
							</span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="">
                        <a href="faq.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            FAQ
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="error-404.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Error 404
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="error-500.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Error 500
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="grid.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Grid
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="blank.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            Blank Page
                        </a>

                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>
        </ul><!-- /.nav-list -->

        <!-- #section:basics/sidebar.layout.minimize -->
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>

        <!-- /section:basics/sidebar.layout.minimize -->
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
        </script>
    </div>

    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <!-- #section:basics/content.breadcrumbs -->
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>

                <li>
                    <a href="#">Forms</a>
                </li>
                <li class="active">Form Elements</li>
            </ul><!-- /.breadcrumb -->

            <!-- #section:basics/content.searchbox -->
            <div class="nav-search" id="nav-search">
                <form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
								<i class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
                </form>
            </div><!-- /.nav-search -->

            <!-- /section:basics/content.searchbox -->
        </div>

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
                        Form Elements
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            Common form elements and layouts
                        </small>
                    </h1>
                </div><!-- /.page-header -->

                <form:form id="userForm" action="${user.userId}" method="post" modelAttribute="user">
                <table align="center">
                    <tr><td colspan="2"><h2 align="center">修改</h2></td></tr>
                    <tr>
                        <td>学号</td>
                        <td><form:input path="userId" value="${user.userId}" Readonly="Readonly" /><form:errors path="userId" cssClass="error" cssStyle="color:red;" /></td>
                    </tr>
                    <tr>
                        <td>姓名</td>
                        <td><form:input path="userName" /><form:errors path="userName" cssClass="error" cssStyle="color:red;" /></td>
                    </tr>
                        <%-- <tr>
                            <td>性别</td>
                            <td><form:input path="gender" />
                                <form:errors path="gender" cssClass="error" cssStyle="color:red;" /></td>
                        </tr> --%>
                    <tr>
                        <td>性别</td>
                        <td><form:radiobutton path="gender" value="0"/>男
                            <form:radiobutton path="gender" value="1"/>女
                            <form:errors path="gender" cssClass="error" cssStyle="color:red;" /></td>
                    </tr>
                    <tr>
                        <td>出生年月</td>
                        <td><form:input path="birthday" /><form:errors path="birthday" cssClass="error" cssStyle="color:red;" /></td>
                    </tr>
                    <tr>
                        <td>电话</td>
                        <td><form:input path="tel" /><form:errors path="tel" cssClass="error" cssStyle="color:red;" /></td>
                    </tr>
                    <tr>
                        <td>住址</td>
                        <td><form:input path="address" /><form:errors path="address" cssClass="error" cssStyle="color:red;" /></td>
                    </tr>

                    <tr><td colspan="2">
                        <button class="btn" type="Submit">
                            Edit
                        </button></td>
                    </tr>

                    <tr><td colspan="2">
                        <button class="btn" type="reset" style="position: relative;top:-40px;left:170px;">
                            Reset
                        </button></td>
                    </tr>
                </table>
                </form:form>

                <!-- basic scripts -->

                <!--[if !IE]> -->
                <script type="text/javascript">
                    window.jQuery || document.write("<script src='${pageContext.request.contextPath}/static/assets/js/jquery.min.js'>"+"<"+"/script>");
                </script>

                <!-- <![endif]-->

                <!--[if IE]>
                <script type="text/javascript">
                    window.jQuery || document.write("<script src='../assets/js/jquery1x.min.js'>"+"<"+"/script>");
                </script>
                <![endif]-->
                <script type="text/javascript">
                    if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/static/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
                </script>
                <script src="${pageContext.request.contextPath}/static/assets/js/bootstrap.min.js"></script>

                <!-- page specific plugin scripts -->

                <!--[if lte IE 8]>
                <script src="../assets/js/excanvas.min.js"></script>
                <![endif]-->
                <script src="${pageContext.request.contextPath}/static/assets/js/jquery-ui.custom.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/jquery.ui.touch-punch.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/chosen.jquery.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/fuelux/fuelux.spinner.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/date-time/bootstrap-datepicker.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/date-time/bootstrap-timepicker.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/date-time/moment.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/date-time/daterangepicker.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/bootstrap-colorpicker.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/jquery.knob.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/jquery.autosize.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/jquery.maskedinput.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/bootstrap-tag.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/typeahead.jquery.min.js"></script>

                <!-- ace scripts -->
                <script src="${pageContext.request.contextPath}/static/assets/js/ace-elements.min.js"></script>
                <script src="${pageContext.request.contextPath}/static/assets/js/ace.min.js"></script>

                <!-- inline scripts related to this page -->
                <script type="text/javascript">
                    jQuery(function($) {
                        $('#id-disable-check').on('click', function() {
                            var inp = $('#form-input-readonly').get(0);
                            if(inp.hasAttribute('disabled')) {
                                inp.setAttribute('readonly' , 'true');
                                inp.removeAttribute('disabled');
                                inp.value="This text field is readonly!";
                            }
                            else {
                                inp.setAttribute('disabled' , 'disabled');
                                inp.removeAttribute('readonly');
                                inp.value="This text field is disabled!";
                            }
                        });


                        $('.chosen-select').chosen({allow_single_deselect:true});
                        //resize the chosen on window resize

                        $(window)
                                .off('resize.chosen')
                                .on('resize.chosen', function() {
                                    $('.chosen-select').each(function() {
                                        var $this = $(this);
                                        $this.next().css({'width': $this.parent().width()});
                                    })
                                }).trigger('resize.chosen');


                        $('#chosen-multiple-style').on('click', function(e){
                            var target = $(e.target).find('input[type=radio]');
                            var which = parseInt(target.val());
                            if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
                            else $('#form-field-select-4').removeClass('tag-input-style');
                        });


                        $('[data-rel=tooltip]').tooltip({container:'body'});
                        $('[data-rel=popover]').popover({container:'body'});

                        $('textarea[class*=autosize]').autosize({append: "\n"});
                        $('textarea.limited').inputlimiter({
                            remText: '%n character%s remaining...',
                            limitText: 'max allowed : %n.'
                        });

                        $.mask.definitions['~']='[+-]';
                        $('.input-mask-date').mask('99/99/9999');
                        $('.input-mask-phone').mask('(999) 999-9999');
                        $('.input-mask-eyescript').mask('~9.99 ~9.99 999');
                        $(".input-mask-product").mask("a*-999-a999",{placeholder:" ",completed:function(){alert("You typed the following: "+this.val());}});



                        $( "#input-size-slider" ).css('width','200px').slider({
                            value:1,
                            range: "min",
                            min: 1,
                            max: 8,
                            step: 1,
                            slide: function( event, ui ) {
                                var sizing = ['', 'input-sm', 'input-lg', 'input-mini', 'input-small', 'input-medium', 'input-large', 'input-xlarge', 'input-xxlarge'];
                                var val = parseInt(ui.value);
                                $('#form-field-4').attr('class', sizing[val]).val('.'+sizing[val]);
                            }
                        });

                        $( "#input-span-slider" ).slider({
                            value:1,
                            range: "min",
                            min: 1,
                            max: 12,
                            step: 1,
                            slide: function( event, ui ) {
                                var val = parseInt(ui.value);
                                $('#form-field-5').attr('class', 'col-xs-'+val).val('.col-xs-'+val);
                            }
                        });



                        //"jQuery UI Slider"
                        //range slider tooltip example
                        $( "#slider-range" ).css('height','200px').slider({
                            orientation: "vertical",
                            range: true,
                            min: 0,
                            max: 100,
                            values: [ 17, 67 ],
                            slide: function( event, ui ) {
                                var val = ui.values[$(ui.handle).index()-1] + "";

                                if( !ui.handle.firstChild ) {
                                    $("<div class='tooltip right in' style='display:none;left:16px;top:-6px;'><div class='tooltip-arrow'></div><div class='tooltip-inner'></div></div>")
                                            .prependTo(ui.handle);
                                }
                                $(ui.handle.firstChild).show().children().eq(1).text(val);
                            }
                        }).find('a').on('blur', function(){
                            $(this.firstChild).hide();
                        });


                        $( "#slider-range-max" ).slider({
                            range: "max",
                            min: 1,
                            max: 10,
                            value: 2
                        });

                        $( "#slider-eq > span" ).css({width:'90%', 'float':'left', margin:'15px'}).each(function() {
                            // read initial values from markup and remove that
                            var value = parseInt( $( this ).text(), 10 );
                            $( this ).empty().slider({
                                value: value,
                                range: "min",
                                animate: true

                            });
                        });

                        $("#slider-eq > span.ui-slider-purple").slider('disable');//disable third item


                        $('#id-input-file-1 , #id-input-file-2').ace_file_input({
                            no_file:'No File ...',
                            btn_choose:'Choose',
                            btn_change:'Change',
                            droppable:false,
                            onchange:null,
                            thumbnail:false //| true | large
                            //whitelist:'gif|png|jpg|jpeg'
                            //blacklist:'exe|php'
                            //onchange:''
                            //
                        });
                        //pre-show a file name, for example a previously selected file
                        //$('#id-input-file-1').ace_file_input('show_file_list', ['myfile.txt'])


                        $('#id-input-file-3').ace_file_input({
                            style:'well',
                            btn_choose:'Drop files here or click to choose',
                            btn_change:null,
                            no_icon:'ace-icon fa fa-cloud-upload',
                            droppable:true,
                            thumbnail:'small'//large | fit
                            //,icon_remove:null//set null, to hide remove/reset button
                            /**,before_change:function(files, dropped) {
						//Check an example below
						//or examples/file-upload.html
						return true;
					}*/
                            /**,before_remove : function() {
						return true;
					}*/
                            ,
                            preview_error : function(filename, error_code) {
                                //name of the file that failed
                                //error_code values
                                //1 = 'FILE_LOAD_FAILED',
                                //2 = 'IMAGE_LOAD_FAILED',
                                //3 = 'THUMBNAIL_FAILED'
                                //alert(error_code);
                            }

                        }).on('change', function(){
                            //console.log($(this).data('ace_input_files'));
                            //console.log($(this).data('ace_input_method'));
                        });


                        //dynamically change allowed formats by changing allowExt && allowMime function
                        $('#id-file-format').removeAttr('checked').on('change', function() {
                            var whitelist_ext, whitelist_mime;
                            var btn_choose
                            var no_icon
                            if(this.checked) {
                                btn_choose = "Drop images here or click to choose";
                                no_icon = "ace-icon fa fa-picture-o";

                                whitelist_ext = ["jpeg", "jpg", "png", "gif" , "bmp"];
                                whitelist_mime = ["image/jpg", "image/jpeg", "image/png", "image/gif", "image/bmp"];
                            }
                            else {
                                btn_choose = "Drop files here or click to choose";
                                no_icon = "ace-icon fa fa-cloud-upload";

                                whitelist_ext = null;//all extensions are acceptable
                                whitelist_mime = null;//all mimes are acceptable
                            }
                            var file_input = $('#id-input-file-3');
                            file_input
                                    .ace_file_input('update_settings',
                                            {
                                                'btn_choose': btn_choose,
                                                'no_icon': no_icon,
                                                'allowExt': whitelist_ext,
                                                'allowMime': whitelist_mime
                                            })
                            file_input.ace_file_input('reset_input');

                            file_input
                                    .off('file.error.ace')
                                    .on('file.error.ace', function(e, info) {
                                        //console.log(info.file_count);//number of selected files
                                        //console.log(info.invalid_count);//number of invalid files
                                        //console.log(info.error_list);//a list of errors in the following format

                                        //info.error_count['ext']
                                        //info.error_count['mime']
                                        //info.error_count['size']

                                        //info.error_list['ext']  = [list of file names with invalid extension]
                                        //info.error_list['mime'] = [list of file names with invalid mimetype]
                                        //info.error_list['size'] = [list of file names with invalid size]


                                        /**
                                         if( !info.dropped ) {
							//perhapse reset file field if files have been selected, and there are invalid files among them
							//when files are dropped, only valid files will be added to our file array
							e.preventDefault();//it will rest input
						}
                                         */


                                        //if files have been selected (not dropped), you can choose to reset input
                                        //because browser keeps all selected files anyway and this cannot be changed
                                        //we can only reset file field to become empty again
                                        //on any case you still should check files with your server side script
                                        //because any arbitrary file can be uploaded by user and it's not safe to rely on browser-side measures
                                    });

                        });

                        $('#spinner1').ace_spinner({value:0,min:0,max:200,step:10, btn_up_class:'btn-info' , btn_down_class:'btn-info'})
                                .on('change', function(){
                                    //alert(this.value)
                                });
                        $('#spinner2').ace_spinner({value:0,min:0,max:10000,step:100, touch_spinner: true, icon_up:'ace-icon fa fa-caret-up', icon_down:'ace-icon fa fa-caret-down'});
                        $('#spinner3').ace_spinner({value:0,min:-100,max:100,step:10, on_sides: true, icon_up:'ace-icon fa fa-plus smaller-75', icon_down:'ace-icon fa fa-minus smaller-75', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});
                        //$('#spinner1').ace_spinner('disable').ace_spinner('value', 11);
                        //or
                        //$('#spinner1').closest('.ace-spinner').spinner('disable').spinner('enable').spinner('value', 11);//disable, enable or change value
                        //$('#spinner1').closest('.ace-spinner').spinner('value', 0);//reset to 0


                        //datepicker plugin
                        //link
                        $('.date-picker').datepicker({
                            autoclose: true,
                            todayHighlight: true
                        })
                        //show datepicker when clicking on the icon
                                .next().on(ace.click_event, function(){
                            $(this).prev().focus();
                        });

                        //or change it into a date range picker
                        $('.input-daterange').datepicker({autoclose:true});


                        //to translate the daterange picker, please copy the "examples/daterange-fr.js" contents here before initialization
                        $('input[name=date-range-picker]').daterangepicker({
                            'applyClass' : 'btn-sm btn-success',
                            'cancelClass' : 'btn-sm btn-default',
                            locale: {
                                applyLabel: 'Apply',
                                cancelLabel: 'Cancel',
                            }
                        })
                                .prev().on(ace.click_event, function(){
                            $(this).next().focus();
                        });


                        $('#timepicker1').timepicker({
                            minuteStep: 1,
                            showSeconds: true,
                            showMeridian: false
                        }).next().on(ace.click_event, function(){
                            $(this).prev().focus();
                        });

                        $('#date-timepicker1').datetimepicker().next().on(ace.click_event, function(){
                            $(this).prev().focus();
                        });


                        $('#colorpicker1').colorpicker();

                        $('#simple-colorpicker-1').ace_colorpicker();
                        //$('#simple-colorpicker-1').ace_colorpicker('pick', 2);//select 2nd color
                        //$('#simple-colorpicker-1').ace_colorpicker('pick', '#fbe983');//select #fbe983 color
                        //var picker = $('#simple-colorpicker-1').data('ace_colorpicker')
                        //picker.pick('red', true);//insert the color if it doesn't exist


                        $(".knob").knob();


                        var tag_input = $('#form-field-tags');
                        try{
                            tag_input.tag(
                                    {
                                        placeholder:tag_input.attr('placeholder'),
                                        //enable typeahead by specifying the source array
                                        source: ace.vars['US_STATES'],//defined in ace.js >> ace.enable_search_ahead
                                        /**
                                         //or fetch data from database, fetch those that match "query"
                                         source: function(query, process) {
						  $.ajax({url: 'remote_source.php?q='+encodeURIComponent(query)})
						  .done(function(result_items){
							process(result_items);
						  });
						}
                                         */
                                    }
                            )

                            //programmatically add a new
                            var $tag_obj = $('#form-field-tags').data('tag');
                            $tag_obj.add('Programmatically Added');
                        }
                        catch(e) {
                            //display a textarea for old IE, because it doesn't support this plugin or another one I tried!
                            tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
                            //$('#form-field-tags').autosize({append: "\n"});
                        }


                        //////////

                        //typeahead.js
                        //example taken from plugin's page at: https://twitter.github.io/typeahead.js/examples/
                        var substringMatcher = function(strs) {
                            return function findMatches(q, cb) {
                                var matches, substringRegex;

                                // an array that will be populated with substring matches
                                matches = [];

                                // regex used to determine if a string contains the substring `q`
                                substrRegex = new RegExp(q, 'i');

                                // iterate through the pool of strings and for any string that
                                // contains the substring `q`, add it to the `matches` array
                                $.each(strs, function(i, str) {
                                    if (substrRegex.test(str)) {
                                        // the typeahead jQuery plugin expects suggestions to a
                                        // JavaScript object, refer to typeahead docs for more info
                                        matches.push({ value: str });
                                    }
                                });

                                cb(matches);
                            }
                        }

                        $('input.typeahead').typeahead({
                            hint: true,
                            highlight: true,
                            minLength: 1
                        }, {
                            name: 'states',
                            displayKey: 'value',
                            source: substringMatcher(ace.vars['US_STATES'])
                        });



                        /////////
                        $('#modal-form input[type=file]').ace_file_input({
                            style:'well',
                            btn_choose:'Drop files here or click to choose',
                            btn_change:null,
                            no_icon:'ace-icon fa fa-cloud-upload',
                            droppable:true,
                            thumbnail:'large'
                        })

                        //chosen plugin inside a modal will have a zero width because the select element is originally hidden
                        //and its width cannot be determined.
                        //so we set the width after modal is show
                        $('#modal-form').on('shown.bs.modal', function () {
                            $(this).find('.chosen-container').each(function(){
                                $(this).find('a:first-child').css('width' , '210px');
                                $(this).find('.chosen-drop').css('width' , '210px');
                                $(this).find('.chosen-search input').css('width' , '200px');
                            });
                        })
                        /**
                         //or you can activate the chosen plugin after modal is shown
                         //this way select element becomes visible with dimensions and chosen works as expected
                         $('#modal-form').on('shown', function () {
					$(this).find('.modal-chosen').chosen();
				})
                         */

                    });
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
</body>
</html>



<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>增加用户</title>
    <script src="${pageContext.request.contextPath}/static/lib/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/lib/jquery.metadata.js"></script>
    <script src="${pageContext.request.contextPath}/static/lib/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/lib/messages_zh.js"></script>
    <!-- jquery validate -->
     <script type="text/javascript">
    $(document).ready(function() {
        $("#userForm").validate();
    });
</script>
</head>
<body>
<form:form id="userForm" action="add" method="post" modelAttribute="user">
    <table align="center">
        <tr><td colspan="2"><h2 align="center">添加</h2></td></tr>
        <tr>
            <td>Student_ID</td>
            <td><form:input path="student_ID" /><form:errors path="student_ID" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><form:input path="name" /><form:errors path="name" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
           <tr>
            <td>性别</td>
            <td><form:input path="gender" /><form:errors path="gender" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
             <tr>
            <td>出生年月</td>
            <td><form:input path="date_of_birth" /><form:errors path="date_of_birth" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
             <tr>
            <td>电话</td>
            <td><form:input path="tel" /><form:errors path="tel" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
             <tr>
            <td>住址</td>
            <td><form:input path="address" /><form:errors path="address" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
        <tr><td colspan="2"><button type="submit">提交</button></td></tr>
    </table>
</form:form>

</body>
</html>  --%>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<title>修改页面</title>
</head>
<body>
<form:form action="${user.student_ID}" method="post" modelAttribute="user">
    <table align="center">
        <tr>
            <td>Student_ID</td>
            <td><form:input path="student_ID" /><form:errors path="student_ID" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><form:input path="name" /><form:errors path="name" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
             <tr>
            <td>性别</td>
            <td><form:input path="gender" /><form:errors path="gender" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
             <tr>
            <td>出生年月</td>
            <td><form:input path="date_of_birth" /><form:errors path="date_of_birth" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
             <tr>
            <td>电话</td>
            <td><form:input path="tel" /><form:errors path="tel" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
             <tr>
            <td>住址</td>
            <td><form:input path="address" /><form:errors path="address" cssClass="error" cssStyle="color:red;" /></td>
        </tr>
        <tr><td colspan="2"><button type="submit">修改</button></td></tr>
    </table>

</form:form>
</body>
</html> --%>