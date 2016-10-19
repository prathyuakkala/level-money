<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Level Money</title>

<c:url var="home" value="/" scope="request" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<spring:url value="/resources/core/js/jquery.1.10.2.min.js" var="jqueryJs" />
<script src="${jqueryJs}"></script>
</head>

<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Level Money</a>
		</div>
	</div>
</nav>

<div class="container" style="min-height: 500px">

	<div class="starter-template">
		<h1>Search Transactions</h1>
		<br>


		<form class="form-horizontal" id="search-form">
			<div class="form-group form-group-lg">
				<div class="radio">
				  <label><input type="radio" name="optradio">Get All Transactions monthly</label>
				</div>
				<div class="radio">
				  <label><input type="radio" name="optradio">Average of Transactions</label>
				</div>
				
			</div>
			<div id="avg-trans">
				<div class="radio">
				  <label><input type="avgRadio" name="optradio" disabled>Monthly</label>
				</div>
				<div class="radio">
				  <label><input type="avgRadio" name="optradio" disabled>Querterly</label>
				</div>
				<div class="radio">
				  <label><input type="avgRadio" name="optradio" disabled>Half Yearly</label>
				</div>
				<div class="radio">
				  <label><input type="avgRadio" name="optradio" disabled>Yearly</label>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" id="bth-search"
						class="btn btn-primary btn-lg">Search</button>
				</div>
			</div>
		</form>
		<div id="transactionsDiv"><h4>Response</h4>
			<div id="transactionsResult" style="overflow-y:scroll; overflow-x:hidden; height:400px;"></div>
		</div>
	</div>

</div>

<script>
	jQuery(document).ready(function($) {

		$("#transactionsDiv").prop('disabled',true);
		$("#search-form").submit(function(event) {

			// Disble the search button
			enableSearchButton(false);

			// Prevent the form from submitting via the browser.
			event.preventDefault();

			searchViaAjax();

		});

	});

	function searchViaAjax() {

		var search = {}
		search["username"] = $("#username").val();
		search["email"] = $("#email").val();

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}search/api/getSearchResult",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});

	}

	function enableSearchButton(flag) {
		$("#btn-search").prop("disabled", flag);
	}

	function display(data) {
		$("#transactionsDiv").prop('disabled',false);
		var json = "<pre>"
				+ JSON.stringify(data, null, 4) + "</pre>";
		$('#transactionsResult').html(json);
	}
</script>

</body>
</html>