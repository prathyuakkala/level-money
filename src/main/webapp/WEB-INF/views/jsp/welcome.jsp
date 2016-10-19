<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Level Money</title>

<c:url var="home" value="/" scope="request" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<spring:url value="/resources/core/js/jquery.1.10.2.min.js" var="jqueryJs" />
<script src="${jqueryJs}"></script>
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
<script src="${bootstrapJs}"></script>

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
					<label><input type="radio" name="optradio" id="getAllTrans">Get
						All Transactions monthly</label>
				</div>
				<div class="radio">
					<label><input type="radio" name="optradio" id="igndou">Ignore
						Dougnuts</label>
				</div>
				<div class="radio">
					<label><input type="radio" name="optradio" id="igncc">Ignore
						Credit Card Payments</label>
				</div>

			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" id="bth-search"
						class="btn btn-primary btn-lg">Search Transactions</button>
				</div>
			</div>
		</form>
		<div id="transactionsDiv">
			<h4>Response</h4>
			<div id="transactionsResult"
				style="overflow-y: scroll; overflow-x: hidden; height: 400px;"></div>
		</div>
	</div>

</div>
 <div class="top-big-link">
  	<a class="btn btn-link-1 launch-modal" href="#" data-modal-id="modal-login">Modal Login</a>
  </div>
<!-- MODAL -->
<div class="modal fade" id="modal-login" tabindex="-1" role="dialog"
	aria-labelledby="modal-login-label" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h3 class="modal-title" id="modal-login-label">Login to our
					site</h3>
				<p>Enter your username and password to log on:</p>
			</div>

			<div class="modal-body">

				<form role="form" action="" method="post" class="login-form">
					<div class="form-group">
						<label class="sr-only" for="form-username">Username</label> <input
							type="text" name="form-username" placeholder="Username..."
							class="form-username form-control" id="form-username">
					</div>
					<div class="form-group">
						<label class="sr-only" for="form-password">Password</label> <input
							type="password" name="form-password" placeholder="Password..."
							class="form-password form-control" id="form-password">
					</div>
					<button type="submit" class="btn">Sign in!</button>
				</form>

			</div>

		</div>
	</div>
</div>
<script>
	jQuery(document).ready(function($) {

		$("#btn-search").prop('disabled',true);
		$("#search-form").submit(function(event) {

			if($("#form-username").val()) {
				$( '#' + $(this).data('modal-id') ).modal();
			} else {
				// Disble the search button
				enableSearchButton(false);
	
				// Prevent the form from submitting via the browser.
				event.preventDefault();
	
				if( $("#getAllTrans").is(':checked') ) {
					searchViaAjax("search/api/getSearchResult");	
				} else if( $("#getAllTrans").is(':checked') ) {
					searchViaAjax("search/api/getSearchResultWithoutDougnuts");	
				} else if( $("#getAllTrans").is(':checked') ) {
					searchViaAjax("search/api/getSearchResultWithoutCC");	
				}
			}
			

		});
		$("#getAllTrans").click(function(event) {
			enableSearchButton(true);
			$("#mtly").prop("disabled", true);
			$("#qter").prop("disabled", true);
			$("#hlf").prop("disabled", true);
			$("#yrly").prop("disabled", true);
		});
		$("#avgTrans").click(function(event) {
			enableSearchButton(true);
			$("#mtly").prop("disabled", false);
			$("#qter").prop("disabled", false);
			$("#hlf").prop("disabled", false);
			$("#yrly").prop("disabled", false);
			
		});
	    /*
		    Form validation
		*/
		$('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
			$(this).removeClass('input-error');
		});
		
		$('.login-form').on('submit', function(e) {
			
			$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
				if( $(this).val() == "" ) {
					e.preventDefault();
					$(this).addClass('input-error');
				}
				else {
					$(this).removeClass('input-error');
				}
			});
			
		});
		 /*
	    	Modals
		*/
		$('.launch-modal').on('click', function(e){
			e.preventDefault();
			$( '#' + $(this).data('modal-id') ).modal();
		});

	});

	function searchViaAjax(url) {
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}"+url,
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