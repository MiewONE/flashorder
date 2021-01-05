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
                if(confirm("상호명 :"+data.restaurantName+"쿠폰번호 : "+datasink+"\n 상호명과 쿠폰번호가 맞다면 예를 눌러 이동합니다."))
                {
                    location.href=data.link;
                }else
                {
                    alert("다시한번 눌러주세요");
                }

            }).fail(function(data){
                alert('실패했어요. 다시한번 누르거나 이미지를 확인해주세요.')
            })
        })
    }
}
index.init();