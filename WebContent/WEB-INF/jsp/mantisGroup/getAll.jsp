<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
	
		<link href="${context}/static/css/jquery-ui.css" rel="stylesheet" type="text/css" />
		<link href="${context}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css" >
		<link href="${context}/static/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" >
		<link href="${context}/static/css/jquery.tagit.css" rel="stylesheet" type="text/css" />
		<link href="${context}/static/css/mantismonitor.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="container-fluid">
		
		<div id="msgError" class="alert alert-danger alert-dismissable">${error}</div>
		<div id="msgSuccess" class="alert alert-success alert-dismissable">${success}</div>
		
		${session.userSession.user.login}
	
		<!-- MANTIS GROUP ADD (BEGIN) -->
		<div class="row row-separate">
			<div class="col-md-6">		
				<form name="frmMantisGroup" id="frmMantisGroup" role="form" class="form-inline">
					<label class="sr-only" for="mantisGroup_name">Group Name</label>
					<input type="text" name="mantisGroup.name" id="mantisGroup_name" size="80" placeholder="Group Name" class="form-control"/>
					<button type="submit" id="btAddMantisGroup" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span></button>
				</form>
			</div>
		
		<!-- MANTIS GROUP ADD (END) -->
		
		<!-- MANTIS GROUP REFRESH (BEGIN) -->

			<div class="col-md-6">		
				<button type="button" id="btnRefreshAll" class="btn btn-default pull-right"><span class="glyphicon glyphicon-refresh"></span></button>
			</div>
		<!-- MANTIS GROUP REFRESH (END) -->
		</div>
	
		<!-- MANTIS GROUP LIST (BEGIN) -->
		
		<div class="row row-separate">
		<div class="col-md-12" id="mantisGroupList">
		<c:forEach items="${mantisGroupList}" var="mantisGroup">
			<div class="mantisGroup panel panel-default" id="mantisGroup_${mantisGroup.id}" data-mantis-group-id="${mantisGroup.id}">
				<div class="panel-heading">
				<div class="row">
					<div class="col-md-11"><h3 class="panel-title">${mantisGroup.name}</h3></div>
					<div class="col-md-1"><a href="${context}/${mantisGroup.id}" class="delete btn btn-xs btn-danger pull-right"><span class="glyphicon glyphicon-minus"></span></a></div>
				</div>
				</div>
				<div class="panel-body">
				<div class="row">
					<div class="col-md-12">
					<ul id="mantisGroupList_${mantisGroup.id}_mantis_ids" class="mantisTags">
						<!-- TAG MANTIS IDS (BEGIN) -->
						<c:forEach items="${mantisGroup.mantisList}" var="mantis">
							<li>${mantis.id}</li>
						</c:forEach>
					</ul>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
					<table id="mantisGroupList_${mantisGroup.id}_mantisList" class="mantisList table table-striped table-bordered">
						<thead>
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th>Status</th>
								<th>Reporter</th>
								<th>Responsable</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${mantisGroup.mantisList}" var="mantis">
						<tr id="mantisGroupList_${mantisGroup.id}_mantis_${mantis.id}" class="mantis" data-mantis-group-id="${mantisGroup.id}" data-mantis-id="${mantis.id}">
							<td class="id">${mantis.id}</td>
							<td class="name">${mantis.name}</td>
							<td class="status">${mantis.status}</td>
							<td class="userReporter">${mantis.userReporter}</td>
							<td class="userResponsable">${mantis.userResponsable}</td>
						</tr>
						</c:forEach>
						</tbody>
					</table>
					</div>
				</div>
				</div>
			</div>
		</c:forEach>
		</div>
		</div>
		
		</div>
		
		<!-- MANTIS GROUP LIST (END) -->

		<%@ include file="/static/html/mantisGroupTmpl.html" %>

	</body>
	<script src="${context}/static/js/jquery-1.11.0.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="${context}/static/js/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="${context}/static/js/bootstrap.min.js" charset="utf-8"></script>
	<script src="${context}/static/js/jquery.tmpl.min.js"></script>
	<script src="${context}/static/js/tag-it.js" type="text/javascript" charset="utf-8"></script>
	<script src="${context}/static/js/mantismonitor.js" type="text/javascript" charset="utf-8"></script>
	<script>
		$(function() {
			$("#btAddMantisGroup").click(function() {
				var mantisGroup = $("#frmMantisGroup").serialize();
			
				$.post("${context}/add", mantisGroup, function(response) {
					if (typeof(response.mantisGroup) !== "undefined") {
						msg.success("Group added with success", 15);
						$.tmpl("mantisGroupTmpl", response).appendTo("#mantisGroupList");

						refreshEvents();
						$("#mantisGroup_name").val("");
					}
				});

				return false;
			});

			function configDelete() {
				$(".delete").click(function() {
					var $this = $(this); 
					
					$.delete_($this.attr("href"), function(response) {
						if (typeof(response.success) !== "undefined") {
							$this.parents(".mantisGroup").get(0).remove();
						}
					});
					return false;
				});
			}

			function refreshEvents() {
				$(".mantisTags").tagit({
					placeholderText: "Type Mantis IDs",
					beforeTagAdded: function(event, ui) {
				        return $.isNumeric(ui.tagLabel);
				    },
					afterTagAdded: function(event, ui) {
						if (ui.duringInitialization) {
							return;
						}
						
						console.log(ui);
						var $this = $(this);
						
						var mantisGroupId = $this.parents(".mantisGroup").attr("data-mantis-group-id");
						var mantisId = ui.tagLabel;
						
						$.post("${context}/"+ mantisGroupId +"/add/"+mantisId, function(response) {
							if (typeof(response.success) !== "undefined") {
								msg.success("Mantis added with success", 15);
								$.tmpl("mantisTmpl", response).appendTo("#mantisGroupList_"+ mantisGroupId +"_mantisList");
							}
						});
					},
					beforeTagRemoved: function(event, ui) {
						console.log(ui);
						var $this = $(this);
						
						var mantisGroupId = $this.parents(".mantisGroup").attr("data-mantis-group-id");
						var mantisId = ui.tagLabel; 

						$("#mantisGroupList_"+mantisGroupId+"_mantis_"+mantisId).remove();
						
						$.delete_("${context}/"+ mantisGroupId +"/remove/"+mantisId, function(response) {
							if (typeof(response.success) !== "undefined") {
								msg.success("Mantis removed with success", 15);
							} else {
								msg.error("Mantis not removed", 15);
							} 
						});
						
						return true;
					}
				});
				configDelete();
			}

			function refreshMantisGroup(mantisGroupId) {
				var el = $("#mantisGroupList_"+mantisGroupId);

				$.get("${context}/"+ mantisGroupId + "/refresh", function(response) {
					if (response.mantisGroup && response.mantisGroup.mantisList) {
						var mantisGroup = response.mantisGroup;
						
						for (var i = 0; i < mantisGroup.mantisList.length; i++) {
							var mantis = response.mantisGroup.mantisList[i];

							$("tr[data-mantis-id="+ mantis.id +"] .id").text(mantis.id);
							$("tr[data-mantis-id="+ mantis.id +"] .name").text(mantis.name);
							$("tr[data-mantis-id="+ mantis.id +"] .status").text(mantis.status);
							$("tr[data-mantis-id="+ mantis.id +"] .userReporter").text(mantis.userReporter);
							$("tr[data-mantis-id="+ mantis.id +"] .userResponsable").text(mantis.userResponsable);
						}
					} 
				});
			}

			refreshEvents();

			function refreshAll() {
				$(".mantisGroup").each(function(index, el) {
					refreshMantisGroup($(this).attr("data-mantis-group-id"));
				});
			}

			$("#btnRefreshAll").click(function() {
				refreshAll();
			});
			
			$.template("mantisGroupTmpl", $("#mantisGroupTmpl"));
			$.template("mantisTmpl", $("#mantisTmpl"));

			var refreshTimer = setInterval(function() {
				refreshAll();
			}, 1*60*1000);
			
		});
	</script>
</html>