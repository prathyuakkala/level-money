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
			<!-- Bad way to do this ... stil this is not proority  -->
			
				<input type="hidden" id="username" name="username"/>
				<input type="hidden" id="password" name="password"/>
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
 <div class="top-big-link" id="modal-link">
  	<a class="btn btn-link-1 launch-modal" href="#" data-modal-id="modal-login"></a>
  </div>

<script>
	jQuery(document).ready(function($) {
		$("#btn-search").prop('disabled',true);
		$("#search-form").submit(function(event) {
			$('#transactionsResult').html("");
			if( !$("#getAllTrans").is(':checked') && !$("#igndou").is(':checked') && !$("#igncc").is(':checked') ) {
				$('#transactionsResult').html(" Please select any one of the options above.... ");
				return false;
			}				
			if( $("#username").val() === "" || $("#password").val() === "" ) {
				 $('#modal-link').find('a').trigger('click');
				 return false;
			} 
			// Disble the search button
			enableSearchButton(false);
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			if( $("#getAllTrans").is(':checked') ) {
				searchViaAjax("search/api/getSearchResult");	
			} else if( $("#igndou").is(':checked') ) {
				searchViaAjax("search/api/getSearchResultWithoutDougnuts");	
			} else if( $("#igncc").is(':checked') ) {
				searchViaAjax("search/api/getSearchResultWithoutCC");	
			}
		});
	    /*
		    Form validation
		*/
		$('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
			$(this).removeClass('input-error');
		});
		
		$('.login-form').on('submit', function(e) {
			$('.close').click()
			$("#password").val($("#form-password").val());
			$("#username").val($("#form-username").val());
			// Disble the search button
			enableSearchButton(false);
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			if( $("#getAllTrans").is(':checked') ) {
				searchViaAjax("search/api/getSearchResult");	
			} else if( $("#igndou").is(':checked') ) {
				searchViaAjax("search/api/getSearchResultWithoutDougnuts");	
			} else if( $("#igncc").is(':checked') ) {
				searchViaAjax("search/api/getSearchResultWithoutCC");	
			}
		
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
		var search = {};
		search["password"] = $("#form-password").val();
		search["email"] = $("#form-username").val();
		$.ajax({
			type : "POST",
			contentType : "application/json",
			data : JSON.stringify(search),
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
		var json = "<pre>"
				+ JSON.stringify(data, null, 4) + "</pre>";
		$('#transactionsResult').html(json);
	}

</script>

</body>
</html>