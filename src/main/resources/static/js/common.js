$(document).ready(function(){
    $('#loading').hide();
}).ajaxStart(function(){
    $('#loading').show();
}).ajaxStop(function(){
    $('#loading').hide();
});
var index={
    init:function()
    {
        $('#btn_push').on('click',function(){
            $.ajax({
                type:'POST',
                url:'/api/getOrderNo',
                dataType:'json',
                contentType:'application/json; charset=utf-8',
                data:$('#urlLink').val()
            }).done(function(data){
                var datasink =data.orderNo;
                var t = document.createElement("textarea");
                document.body.appendChild(t);
                t.value = datasink;
                t.select();
                document.execCommand('copy');
                document.body.removeChild(t);

                location.href=data.link;
            }).fail(function(data){
                alert('실패했어요. 다시한번 누르거나 이미지를 확인해주세요.')
            })
        })
    }
}
index.init();