<%-- 
    Document   : index
    Created on : 26/05/2019, 12:47:50 AM
    Author     : Juan Arenas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
        <jsp:include page="public/html/styles.html" />
    </head>
    <body> 
        <jsp:include page="public/html/header/header.html" />
        <!-- Container-->
        <div class="container-fluid"> 
            <div class="table-responsive">
                <table class="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>RFC</th>
                            <th>Razon social</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="tbl_clientes">
                         
                    </tbody>
                </table>
            </div>
        </div>
        <!-- End Container-->
        <jsp:include page="public/html/footer/footer.html" />
        <jsp:include page="public/html/js.html" />
        <script src="public/js/global/configuracion.js"></script>
        <script src="public/clientes/ajax.js"></script>
        <script src="public/clientes/js.js"></script>
    </body>
</html>
