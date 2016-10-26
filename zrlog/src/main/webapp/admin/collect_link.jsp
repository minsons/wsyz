<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="include/menu.jsp"/>
<link rel="stylesheet" href="assets/css/ui.jqgrid.css" />

<script src="assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="admin/layer/layer.js"></script>

<div class="page-header">
	<h3>
		收藏夹链接
	</h3>
</div><!-- /.page-header -->
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
        <button id="addNew" >新增记录 </button>
        
		<table id="grid-table"></table>

		<div id="grid-pager"></div>

		<!-- PAGE CONTENT ENDS -->
	</div><!-- /.col -->
	

    <div id="showDilog"  style="display:none;">
     	
    </div>



</div>
<script src="admin/js/collect_link_jqgrid.js"></script>
<jsp:include page="include/footer.jsp"/>
