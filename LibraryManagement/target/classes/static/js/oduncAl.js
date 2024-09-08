$(document).ready(function() {
    // Üye arama kutusuna input girildiğinde çalışır
    $("#uyeArama").on("input", function() {
        let query = $(this).val();
        if (query.length > 2) {  // Minimum 3 karakterden sonra arama yapılır
            $.ajax({
                url: "/uye/arama",  // URL Postman'deki gibi, doğru rotayı kullanıyoruz
                method: "GET",
                data: { q: query },  // 'q' parametresi backend'e gönderiliyor
                success: function(uyeler) {
                    let sonucHtml = '';
                    $.each(uyeler, function(_, uye) {
                        // Her bir üye için HTML oluşturuluyor
                        sonucHtml += '<li>' + uye.ad + ' ' + uye.soyad +
                                     ' <button onclick="secUye(' + uye.uyeId + ', \'' + uye.ad + ' ' + uye.soyad + '\')">Seç</button></li>';
                    });
                    // Sonuçlar HTML'e ekleniyor
                    $("#uyeSonuclari").html(sonucHtml);
                },
                error: function(xhr, status, error) {
                    console.error("Arama sırasında hata: " + error); // Hata tarayıcı konsoluna yazdırılır
                    alert("Arama sırasında bir hata oluştu.");
                }
            });
        } else {
            // Eğer arama metni 3 karakterden azsa sonuçları temizle
            $("#uyeSonuclari").html('');
        }
    });
});

// Üye seçildiğinde üyenin ID'si ve ismi gizli alana kaydedilir
function secUye(uyeId, uyeIsmi) {
    $("#uyeId").val(uyeId);  // Üyenin ID'sini gizli input alanına kaydet
    $("#uyeArama").val(uyeIsmi);  // Arama kutusuna üyenin ismini yazdır
    $("#uyeSonuclari").html('');  // Sonuçları temizle
}

// Kitap ödünç alma işlemi
function kitapOduncAl(kitapId) {
    let uyeId = $("#uyeId").val();
    if (!uyeId) {
        alert("Lütfen önce bir üye seçin.");
        return;
    }
    $.ajax({
        url: "/kitapAlim/oduncAl/" + uyeId + "/" + kitapId,
        method: "POST",
        success: function() {
            alert("Kitap başarıyla ödünç alındı.");
            window.location.reload();
        },
        error: function() {
            alert("Bir hata oluştu. Lütfen tekrar deneyin.");
        }
    });
}
