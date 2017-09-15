/**
 * Created by admin on 2015/10/26.
 */

$('#context1 .menu .item')
    .tab({
        context: $('#context1')
    })
;
$('.ui.dropdown')
    .dropdown()
;
$('.ui.radio.checkbox')
    .checkbox()
;
$('.ui.checkbox')
    .checkbox()
;
var code =0;
$('.verify-code').click(function(e){
    e.preventDefault();
    $.ajax({
        url:'/OCare/app/code',
        cache: false,
        type: 'get',
        data: 'phoneNum='+$('#legalperson_tel').val(),
        success:function(data){
            alert("verify code has been sent");
            code = data.code;
            console.log(code);
        },
        error: function(error){
            alert("failed to send the verify code");
        }
    })
});

//$('.leagal-submit').on('click',function(e){
//    e.preventDefault();
//    $.ajax({
//        url: '/OCare/app/register/legalperson',
//        type: 'get',
//        cache: false,
//        async: false,
//        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
//        processData: false,
//        data: 'lpId='+$('#legalperson_id').val()+'&lpName='+$('#legalperson_name').val()+'&lpPhone='+$('#legalperson_tel').val()+'&lpEmail='+$('#legalperson_email').val()+'&lpPassword='+$('#legalperson_password').val()+'&lpImage='+$('#id_image').val(),
//        dataType: 'JSON',
//        success: function(data){
//            if(data.error == true){
//                alert(data.errorMsg);
//            }
//            var sent_code = $('#verification').val();
//            if( sent_code != code || sent_code == ""){
//                alert("verify code is empty/wrong!")
//            }
//            else{
//                alert("submitted!");
//                document.forms[0].action = "/OCare/pages/index.jsp";
//                document.forms[0].submit();
//                }
//            },
//        error:function(data){
//            alert("submit failed!");
//        }
//    });
//})


