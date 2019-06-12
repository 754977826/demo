$(document).ready(function () {
    $("#upBut").on("click", function () {
        $("#upMsg").html("正在上传。。").css("color", "black");
        $.ajax({
            url: "/document/saveDocumentFile",
            type: 'POST',
            cache: false,
            data: new FormData($('#upForm')[0]),
            processData: false,
            contentType: false,
            dataType: "json",
            success: function (data) {
                console.log(JSON.stringify(data));
                var upMsg = $("#upMsg");
                if(data.errCode !== "0"){
                    upMsg.css("color", "red");
                }
                upMsg.html(data.errInfo);
            },
            error: function (data) {
                $("#upMsg").html("上传失败！").css("color", "red");
            },
        });
    });
});
