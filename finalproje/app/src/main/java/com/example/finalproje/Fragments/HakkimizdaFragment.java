package com.example.finalproje.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalproje.R;

public class HakkimizdaFragment extends Fragment {
    TextView txtHakkinda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hakkimizda, container, false);

        txtHakkinda =view.findViewById(R.id.txtHakkinda);
        txtHakkinda.setText("-biBilet; otobüs ve uçak bileti alırken firmaları kıyaslayabilmeniz ve size en uygun otobüs ve uçak biletini kolayca alabilmeniz için 2012 yılında Ali Yılmaz ve Yiğit Gürocak tarafından kuruldu.\n" +"\n"+
                "-2015 yılında Aslanoba Capital ve Earlybird Venture Capital şirketleri tarafından yatırım alarak, grup şirketleri bünyesine katıldı.\n" +"\n"+
                "-Her biri işinde uzman ekip arkadaşlarını da bünyesine katarak kocaman bir aile haline geldi.\n" +"\n"+
                "-Kullanıcıları en uygun bileti ararken ve alırken hiç zorlanmasın diye çok kolay kullanılabilen ve 2 dakika içerisinde otobüs ve uçak biletinizi üye olmadan satın alabileceğiniz, mobil uyumlu harika bir web sitesi ve harika mobil uygulamalar hazırladı.\n" +"\n"+
                "-Türkiye’nin en seçkin otobüs firmalarını ve hava yolu firmalarını bünyesine katarak, kullanıcılarına; en iyi firmaları kıyaslama ve kendilerine en uygun otobüs biletini ve uçak biletini bulma imkanı sağladı.\n" +
                "-Kullanıcıları biletlerini alırken tamamen güvende olabilsin diye, kullanıcılarının tüm işlemlerini 128-bit SSL ile şifreledi ve ABD’nin en büyük internet güvenlik şirketlerinden biri olan COMODO firması ile anlaşarak tüm işlemlerin sigortalanmasını sağladı.\n" +"\n"+
                "-Kullanıcıları hem biletlerini ararken hem de aldıktan sonra kendilerini yalnız hissetmesin diye mükemmel bir Canlı Destek ekibi kurdu ve bu ekiple birlikte; ister site üzerinden canlı destek hattına bağlanan, ister müşteri hizmetleri numarasını arayan kullanıcılarına ihtiyaç duydukları her konuda 7/24 destek verdi.\n" +"\n"+
                "-Kullanıcıları hangi firma ile seyahat edeceğine karar verirken zorlanmasın diye, seyahatleri sonrasında yolcularına firmalar hakkında yorumlarını sordu ve bu yorumları otobüs firmalarının sayfalarında kullanıcılarıyla paylaştı.\n" +"\n"+
                "-Kullanıcıları otobüs biletlerini alırken, otobüse hangi yönden güneş vuracağını hesaplamak zorunda kalmasın diye, bir ilke imza atarak, otobüse hangi yönden güneş vuracağı bilgisini sistemine ekledi.\n" +"\n"+
                "-2015 yılında obilet’in Mobil Uygulamaları MasterCard tarafından Avrupa’nın En İyi Seyahat Uygulaması seçildi.\n" +"\n"+
                "-2016 yılında obilet.com, çocukların yararına düzenlenen 5. Hayalimi Paylaş Yeditepe Dilek Ödülleri gecesinde 2016 yılının ‘En İyi Web Sitesi’ ödülünü aldı.\n" +"\n"+
                "-2017’de dünyanın önde gelen denetleme firması Deloitte tarafından gerçekleştirilen Deloitte Teknoloji Fast 50 Türkiye 2017 Programı’nda %1041 büyüme oranı ile ‘Türkiye’nin En Çok Büyüyen 8. Teknoloji Şirketi’ seçildi.\n" +"\n"+
                "-biBlet.com, 2018 yılının ikinci çeyreğinde, en çok yatırım alan yerli şirket oldu.\n" +"\n"+
                "-biBilet.com ayda 16 milyondan fazla kullanıcı tarafından ziyaret ediliyor.\n" +"\n"+
                "-biBilet Mobil Uygulamaları 3 Milyondan fazla kullanıcı tarafından cep telefonlarına indirildi.\n" +"\n"+
                "-biBilet.com; Türkiye Odalar ve Borsalar Birliği (TOBB) ve Türkiye Ekonomi Politikaları Araştırma Vakfı (TEPAV) tarafından düzenlenen araştırmanın sonucuna göre Türkiye’nin en hızlı büyüyen 100 şirketi arasında yer aldı.\n" +"\n"+
                "-2019 yılının 4. çeyreğinde Avrupa İmar ve Kalkınma Bankası’nı da yatırımcıları arasına kattı.\n" +"\n"+
                "-2019’da toplam 30 kategoride gerçekleşen 2019 Uzakrota Ödülleri’nde, halk oylaması sonucu “Yılın Yerli Seyahat Girişimi (Scaleup)” ve “Yılın Otobüs Rezervasyon Sitesi” seçildi.\n" +"\n"+
                "-Altın Örümcek Web Ödülleri’nde 2019 “Mobil Uygulama & Alışveriş” kategorisinde “En İyi Mobil Uygulama” seçilen obilet.com, “Turizm & Seyahat” kategorisinde ise Halkın Favorisi seçildi.\n" +"\n"+
                "-2019 yılında 10 milyondan fazla bilet satan obilet.com, 2020 yılı özelinde EBRD’nin desteği ve ekip arkadaşlarının artan performans ve motivasyonları ile 17-18 milyon bilet satışı hedefliyor.\n" +"\n"+
                "-Gelecek planları arasında yenilikçi uygulamalarını global pazara taşımayı hedefleyen şirket, ilk kurulduğu yıla göre hem bilet satış oranında hem de yıllık trafik bazında 20 kat büyüdü.\n" +"\n"+
                "-biBilet.com, 2020 yılında havayollarının direkt acentesi olarak hizmet vermeye başladı.\n" +"\n"+
                "-Türkiye’nin en başarılı girişimlerinin seçildiği “Startup 100” listesinde 7. sıraya yerleşerek seyahat ve ulaşım sektörlerindeki başarısını bir kez daha kanıtladı.\n" +"\n"+
                "-2020’de Fast Company tarafından hazırlanan “Türkiye’deki internet ekosisteminde cirolarıyla öne çıkan şirketler listesi”nde 15. sırada yerini aldı ve seyahat sektörünün en büyük şirketi oldu.\n" +"\n"+
                "-2020’de 18. kez gerçekleştirilen Altın Örümcek Web Ödülleri kapsamında “Mobil Uygulama&Hizmet” kategorisinde rakiplerini geride bırakarak “en iyi seyahat uygulaması” seçildi.\n" +"\n"+
                "-2020 Deloitte Technology Fast 500™ EMEA listesinde, yüzde 249 büyüme oranıyla obilet.com, seyahat sektöründe Türkiye’nin en iyi yazılım şirketi oldu.\n" +"\n"+
                "-Özetle;\n" +"\n"+
                "-biBilet.com ailesi, kullanıcıları kendilerine en uygun bileti rahat ve güvenli bir şekilde alabilsin, sorunsuz bir şekilde seyahatlerini gerçekleştirebilsin diye elinden gelen her şeyi yaptı ve sizler için gelişmeye ve büyümeye devam ediyor. Şimdi sıra sizde, siz de obilet.com ailesinin sizler için yarattığı imkanlardan faydalanmaya hemen başlayabilirsiniz.");

        return view;
    }
}