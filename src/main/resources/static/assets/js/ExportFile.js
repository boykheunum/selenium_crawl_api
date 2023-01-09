$(document).ready(function() {
    let pathname = window.location.pathname;
    let type = $(location).attr('href');
    if(pathname != null && type != null){
        pathname = pathname.split("/")[2];
        type = $(location).attr('href').split("=")[1];
        let data = {}
        data["pathname"]=pathname;
        data["type"]=type;
        $("#export_csv").click(function () {
            alert(type);
            $.ajax({
                type: "POST",
                url:"/hacom/csv_export",
                contentType: "application/json",
                data:JSON.stringify(data),
            })
        })

        $("#export_excel").click(function(){
            $.ajax({
                type: "POST",
                url:"/hacom/excel_export",
                contentType: "application/json",
                data:JSON.stringify(data),
            })
        })
    }


})