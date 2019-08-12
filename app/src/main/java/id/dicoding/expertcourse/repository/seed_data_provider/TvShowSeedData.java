package id.dicoding.expertcourse.repository.seed_data_provider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.dicoding.expertcourse.model.BaseMovie;
import id.dicoding.expertcourse.model.TvShow;

public class TvShowSeedData {
    private static String seedData = "[{\"title\":\"Arrow\",\"banner\":\"poster_arrow.webp\",\"released_year\":\"2012\",\"review_score\":58,\"overview_en\":\"Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.\",\"overview_id\":\"Arrow adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow\",\"status\":0,\"runtime\":\"42m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Doom Patrol\",\"banner\":\"poster_doom_patrol.webp\",\"released_year\":\"2019\",\"review_score\":61,\"overview_en\":\"The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.\",\"overview_id\":null,\"status\":0,\"runtime\":\"60m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Dragon Ball\",\"banner\":\"poster_dragon_ball.webp\",\"released_year\":\"1986\",\"review_score\":71,\"overview_en\":\"Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the dragon balls brought her to Goku's home. Together, they set off to find all seven dragon balls in an adventure that would change Goku's life forever. See how Goku met his life long friends Bulma, Yamcha, Krillin, Master Roshi and more.\",\"overview_id\":\"Dahulu kala di pegunungan, seorang master pertempuran yang dikenal sebagai Gohan menemukan seorang bocah aneh yang ia beri nama Goku. Gohan membesarkannya dan melatih Goku dalam seni bela diri sampai dia mati. Bocah muda dan sangat kuat itu sendirian, tetapi mudah dikelola. Kemudian suatu hari, Goku bertemu dengan seorang gadis remaja bernama Bulma, yang pencariannya untuk bola naga membawanya ke rumah Goku. Bersama-sama, mereka berangkat untuk menemukan ketujuh bola naga dalam sebuah petualangan yang akan mengubah hidup Goku selamanya. Lihat bagaimana Goku bertemu teman-teman seumur hidupnya Bulma, Yamcha, Krillin, Master Roshi dan banyak lagi.\",\"status\":1,\"runtime\":\"25m\",\"original_language_en\":\"Japanese\",\"original_language_id\":\"Bahasa Jepang\"},{\"title\":\"Fairy Tail\",\"banner\":\"poster_fairytail.webp\",\"released_year\":\"2009\",\"review_score\":64,\"overview_en\":\"Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.\",\"overview_id\":null,\"status\":0,\"runtime\":\"25m\",\"original_language_en\":\"Japanese\",\"original_language_id\":\"Bahasa Jepang\"},{\"title\":\"Family Guy\",\"banner\":\"poster_family_guy.webp\",\"released_year\":\"1999\",\"review_score\":65,\"overview_en\":\"Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.\",\"overview_id\":\"Seri animasi animasi Freakin 'Sweet yang sakit, terpelintir, dan salah, menampilkan petualangan keluarga Griffin yang disfungsional. Peter yang kikuk dan Lois yang sudah lama menderita memiliki tiga anak. Stewie (bayi yang brilian tetapi sadis yang bertekad membunuh ibunya dan mengambil alih dunia), Meg (yang tertua, dan merupakan gadis yang paling tidak populer di kota) dan Chris (anak tengah, dia tidak terlalu cerdas tetapi memiliki hasrat untuk film ). Anggota terakhir keluarga itu adalah Brian - anjing yang bisa bicara dan lebih dari sekadar hewan peliharaan, ia menjaga Stewie, sementara menghirup Martinis dan memilah-milah masalah hidupnya sendiri.\",\"status\":0,\"runtime\":\"22m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Game of Thrones\",\"banner\":\"poster_god.webp\",\"released_year\":\"2011\",\"review_score\":81,\"overview_en\":\"Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.\",\"overview_id\":\"Tujuh keluarga bangsawan berjuang untuk menguasai tanah mitos Westeros. Gesekan antara rumah-rumah mengarah ke perang skala penuh. Semua sementara kejahatan yang sangat kuno terbangun di utara terjauh. Di tengah-tengah perang, perintah militer yang diabaikan, Night's Watch, adalah yang berdiri di antara alam manusia dan kengerian es di luarnya.\",\"status\":1,\"runtime\":\"60m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Gotham\",\"banner\":\"poster_gotham.webp\",\"released_year\":\"2014\",\"review_score\":69,\"overview_en\":\"Before there was Batman, there was GOTHAM. veryone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?\",\"overview_id\":\"Semua orang tahu nama Komisaris Gordon. Dia adalah salah satu musuh terbesar dunia kejahatan, seorang pria yang reputasinya identik dengan hukum dan ketertiban. Tapi apa yang diketahui tentang kisah Gordon dan kenaikannya dari detektif pemula ke Komisaris Polisi? Apa yang diperlukan untuk menavigasi berbagai lapisan korupsi yang diam-diam memerintah Kota Gotham, tempat bertelurnya penjahat paling ikonik di dunia? Dan keadaan apa yang menciptakan mereka - persona yang lebih besar dari kehidupan yang akan menjadi Catwoman, The Penguin, The Riddler, Two-Face dan The Joker?\",\"status\":1,\"runtime\":\"43m, 60m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Grey's Anatomy\",\"banner\":\"poster_grey_anatomy.webp\",\"released_year\":\"2005\",\"review_score\":62,\"overview_en\":\"Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.\",\"overview_id\":\"Ikuti kehidupan pribadi dan profesional sekelompok dokter di Rumah Sakit Gray Sloan Memorial di Seattle.\",\"status\":0,\"runtime\":\"43m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Hanna\",\"banner\":\"poster_hanna.webp\",\"released_year\":\"(2019)\",\"review_score\":64,\"overview_en\":\"This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.\",\"overview_id\":null,\"status\":0,\"runtime\":\"50m\",\"original_language_en\":\"Inggris\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Naruto Shippūden\",\"banner\":\"poster_naruto_shipudden.webp\",\"released_year\":\"2007\",\"review_score\":75,\"overview_en\":\"Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.\",\"overview_id\":\"Naruto Shippuuden adalah kelanjutan dari serial TV animasi asli Naruto. Kisah ini berkisah tentang Uzumaki Naruto yang lebih tua dan sedikit lebih matang dan upayanya untuk menyelamatkan temannya Uchiha Sasuke dari cengkeraman Shinobi seperti ular, Orochimaru. Setelah 2 setengah tahun, Naruto akhirnya kembali ke desanya Konoha, dan mulai mewujudkan ambisinya, meskipun itu tidak akan mudah, karena Ia telah mengumpulkan beberapa musuh (lebih berbahaya), seperti organisasi shinobi. ; Akatsuki.\",\"status\":1,\"runtime\":\"22m\",\"original_language_en\":\"Japanese\",\"original_language_id\":\"Bahasa Jepang\"},{\"title\":\"Marvel's Iron Fist\",\"banner\":\"poster_iron_fist.webp\",\"released_year\":\"2017\",\"review_score\":61,\"overview_en\":\"Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.\",\"overview_id\":null,\"status\":0,\"runtime\":\"55m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"NCIS\",\"banner\":\"poster_ncis.webp\",\"released_year\":\"2003\",\"review_score\":67,\"overview_en\":\"From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.\",\"overview_id\":null,\"status\":0,\"runtime\":\"45m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Riverdale\",\"banner\":\"poster_riverdale.webp\",\"released_year\":\"2017\",\"review_score\":69,\"overview_en\":\"Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.\",\"overview_id\":null,\"status\":0,\"runtime\":\"45m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Shameless\",\"banner\":\"poster_shameless.webp\",\"released_year\":\"2011\",\"review_score\":78,\"overview_en\":\"Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.\",\"overview_id\":null,\"status\":0,\"runtime\":\"60m, 55m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Supergirl\",\"banner\":\"poster_supergirl.webp\",\"released_year\":\"2015\",\"review_score\":58,\"overview_en\":\"Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.\",\"overview_id\":null,\"status\":0,\"runtime\":\"42m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Supernatural\",\"banner\":\"poster_supernatural.webp\",\"released_year\":\"2005\",\"review_score\":73,\"overview_en\":\"When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.\",\"overview_id\":\"Dua bersaudara mencari ayah mereka yang hilang, pria yang melatih mereka untuk menjadi prajurit melawan kejahatan supernatural\",\"status\":0,\"runtime\":\"45m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"The Flash\",\"banner\":\"poster_flash.webp\",\"released_year\":\"2014\",\"review_score\":67,\"overview_en\":\"After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.\",\"overview_id\":\"Setelah akselerator partikel menyebabkan badai aneh, Penyelidik CSI Barry Allen disambar petir dan jatuh koma. Beberapa bulan kemudian dia terbangun dengan kekuatan kecepatan super, memberinya kemampuan untuk bergerak melalui Central City seperti malaikat penjaga yang tak terlihat. Meskipun awalnya senang dengan kekuatan barunya, Barry terkejut menemukan bahwa dia bukan satu-satunya manusia meta yang diciptakan setelah ledakan akselerator - dan tidak semua orang menggunakan kekuatan baru mereka untuk kebaikan. Barry bermitra dengan S.T.A.R. Lab dan mendedikasikan hidupnya untuk melindungi yang tidak bersalah. Untuk saat ini, hanya beberapa teman dekat dan rekan yang tahu bahwa Barry secara harfiah adalah manusia tercepat, tetapi tidak lama sebelum dunia mengetahui apa yang menjadi Barry Allen ... The Flash.\",\"status\":0,\"runtime\":\"44m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"The Simpsons\",\"banner\":\"poster_the_simpson.webp\",\"released_year\":\"1989\",\"review_score\":71,\"overview_en\":\"Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.\",\"overview_id\":\"Bertempat di Springfield, kota rata-rata di Amerika, pertunjukan ini berfokus pada kejenakaan dan petualangan sehari-hari keluarga Simpson; Homer, Marge, Bart, Lisa dan Maggie, serta ribuan pemain virtual. Sejak awal, serial ini telah menjadi ikon budaya pop, menarik ratusan selebriti menjadi bintang tamu. Acara ini juga menjadi terkenal karena satirnya yang tak kenal takut terhadap kehidupan politik, media, dan Amerika secara umum.\",\"status\":0,\"runtime\":\"22m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"The Umbrella Academy\",\"banner\":\"poster_the_umbrella.webp\",\"released_year\":\"2019\",\"review_score\":77,\"overview_en\":\"A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.\",\"overview_id\":null,\"status\":0,\"runtime\":\"60m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"The Walking Dead\",\"banner\":\"poster_the_walking_dead.webp\",\"released_year\":\"2010\",\"review_score\":73,\"overview_en\":\"Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.\",\"overview_id\":null,\"status\":0,\"runtime\":\"42m, 60m, 45m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"}]";

    public static List<BaseMovie> getSeedData() {
        try {
            JSONArray rawSeedData = new JSONArray(seedData);
            return extractTvShowsFromJSONArrayToBaseMovieList(rawSeedData);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static TvShow extractTvShowFromJSONObject(JSONObject rawTvShow) throws JSONException {
        TvShow tvShow = new TvShow();
        tvShow.setTitle(rawTvShow.getString("title"));
        tvShow.setBanner(rawTvShow.getString("banner"));
        tvShow.setReleasedYear(rawTvShow.getString("released_year"));
        tvShow.setReviewScore(rawTvShow.getInt("review_score"));
        tvShow.setOverviewEn(rawTvShow.getString("overview_en"));
        tvShow.setOverviewId(rawTvShow.getString("overview_id"));
        tvShow.setStatus(rawTvShow.getInt("status"));
        tvShow.setRuntime(rawTvShow.getString("runtime"));
        tvShow.setOriginalLanguageEn(rawTvShow.getString("original_language_en"));
        tvShow.setOriginalLanguageId(rawTvShow.getString("original_language_id"));

        return tvShow;
    }

    private static List<BaseMovie> extractTvShowsFromJSONArrayToBaseMovieList(JSONArray rawSeedData) throws JSONException {
        List<BaseMovie> tvShows = new ArrayList<>();
        for (int ii = 0; ii < rawSeedData.length(); ii++) {
            JSONObject rawTvShow = rawSeedData.getJSONObject(ii);
            BaseMovie tvShow = extractTvShowFromJSONObject(rawTvShow);
            tvShows.add(tvShow);
        }

        return tvShows;
    }
}
