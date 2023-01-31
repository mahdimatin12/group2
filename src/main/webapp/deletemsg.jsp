<%-- 
    Document   : deletemsg
    Created on : 31-Jan-2023, 2:09:15 PM
    Author     : 236370
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="bg-gray-900">
        <div class="mt-20 p-4 rounded bg-white max-w-sm text-center border-t-4 border-red-600 mx-auto text-gray-700 pt-0">
            <span class="bg-red-600 text-white p-4 rounded-full inline-flex -mt-8 mb-2">
                <svg class="fill-current w-8 h-8" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M6 2l2-2h4l2 2h4v2H2V2h4zM3 6h14l-1 14H4L3 6zm5 2v10h1V8H8zm3 0v10h1V8h-1z"/></svg>
            </span>
            <h3 class="font-bold text-2xl text-black mb-2">Delete Account?</h3>

            <div class="flex pt-8">
                <button href="customeraccount.jsp" class="w-1/2 mr-1 bg-white border text-gray-600 border-gray-400 hover:bg-gray-300 py-2 px-4 rounded font-medium">Cancel</button>
                <button href="/group2/DeleteCustomerServlet" class="w-1/2 ml-1 bg-red-600 border border-red-600 text-white border-gray-500 hover:bg-red-700 hover:border-red-700 py-2 px-4 rounded font-medium">Delete Account</button>
            </div>
        </div>
    </body>
</html>
