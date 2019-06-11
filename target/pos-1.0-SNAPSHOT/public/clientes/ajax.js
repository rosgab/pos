/* global sys */

function ver()
{ 
    $.ajax({
        url: sys+'/clientes',
        data:{
         },
        type:'POST',
        datatype:'JSON',
        beforeSend:function(){
            $("#tbl_clientes").empty();
        },
        success:function(data){
            for(i=0;i<data.length;i++){ 
                rowAdd(data[i],(i+1));
            } 
        } 
    }); 
}


function rowAdd(object,row)
{
    var tr = "";
    
    tr+="<tr>";
    tr+="<td>"+row+"</td>";
    tr+="<td>"+object["rfc"]+"</td>";
    tr+="<td>"+object["razon_social"]+"</td>";
    tr+="<td>";
    tr+="<button class='btn btn-primary'>Ver</button>";
    tr+="</td>";
    tr+="</tr>";
    
    $("#tbl_clientes").append(tr);
}