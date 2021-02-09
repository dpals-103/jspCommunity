<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script>
var alertMsg ='${alertMsg}'.trim();

if(alertMsg){
	alert(alertMsg); 
}

var historyBack ='${historyBack}' == 'true'; 

if(historyBack){
	history.back(); 
}

var replaceUrl = '${replaceUrl}'.trim();

	if (replaceUrl) {
		location.replace(replaceUrl);
	}
	
</script>