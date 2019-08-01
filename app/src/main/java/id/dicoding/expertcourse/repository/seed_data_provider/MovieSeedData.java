package id.dicoding.expertcourse.repository.seed_data_provider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.dicoding.expertcourse.model.Movie;

public class MovieSeedData {
    private static String seedData = "[{\"title\":\"Alita: Battle Angel\",\"banner\":\"poster_alita.webp\",\"released_year\":\"2019\",\"review_score\":67,\"overview_en\":\"When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.\",\"overview_id\":\"Ketika Alita terbangun tanpa ingatan tentang siapa dia di dunia masa depan yang tidak dia kenal, dia ditangkap oleh Ido, seorang dokter yang penuh kasih yang menyadari bahwa di suatu tempat dalam cangkang cyborg yang ditinggalkan ini adalah hati dan jiwa seorang wanita muda dengan luar biasa. lalu.\",\"revenue\":\"$404,852,543.00\",\"budget\":\"$170,000,000.00\",\"runtime\":\"2h 2m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Aquaman\",\"banner\":\"poster_aquaman.webp\",\"released_year\":\"2018\",\"review_score\":68,\"overview_en\":\"Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.\",\"overview_id\":null,\"revenue\":\"$1,143,689,193.00\",\"budget\":\"$160,000,000.00\",\"runtime\":\"2h 24m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"A Star Is Born\",\"banner\":\"poster_a_star.webp\",\"released_year\":\"2018\",\"review_score\":75,\"overview_en\":\"Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.\",\"overview_id\":\"Seorang bintang musik country yang karirnya mulai memudar, Jackson Maine (Bradley Cooper) menemukan sebuah talenta yang sangat berbakat di dalam diri dari seorang musisi muda bernama Ally (Lady Gaga). Maine berhasil mengorbitkan Ally menjadi seorang bintang muda yang menjanjikan. Namun keduanya terlibat hubungan yang lebih jauh dari sekedar mentor dan anak didik. Seiring dengan meroketnya karir dari Ally dan dirinya, Maine mengalami dilema mengenai masalah ini.\",\"revenue\":\"$433,888,866.00\",\"budget\":\"$36,000,000.00\",\"runtime\":\"2h 15m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Avengers: Infinity War\",\"banner\":\"poster_avengerinfinity.webp\",\"released_year\":\"2018\",\"review_score\":83,\"overview_en\":\"As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.\",\"overview_id\":\"Karena Avengers dan sekutunya terus melindungi dunia dari ancaman yang terlalu besar untuk ditangani oleh seorang pahlawan, bahaya baru telah muncul dari bayangan kosmik: Thanos. Seorang lalim penghujatan intergalaksi, tujuannya adalah untuk mengumpulkan semua enam Batu Infinity, artefak kekuatan yang tak terbayangkan, dan menggunakannya untuk menimbulkan kehendak memutar pada semua realitas. Segala sesuatu yang telah diperjuangkan oleh Avengers telah berkembang hingga saat ini - nasib Bumi dan keberadaannya sendiri tidak pernah lebih pasti.\\n\\n\",\"revenue\":\"$2,046,239,637.00\",\"budget\":\"$300,000,000.00\",\"runtime\":\"2h 29m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Bird Box\",\"banner\":\"poster_birdbox.webp\",\"released_year\":\"2018\",\"review_score\":70,\"overview_en\":\"Five years after an ominous unseen presence drives most of society to suicide, a survivor and her two children make a desperate bid to reach safety.\",\"overview_id\":null,\"revenue\":\"-\",\"budget\":\"$19,800,000.00\",\"runtime\":\"2h 4m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Bohemian Rhapsody\",\"banner\":\"poster_bohemian.webp\",\"released_year\":\"2018\",\"review_score\":81,\"overview_en\":\"Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.\",\"overview_id\":null,\"revenue\":\"$835,137,710.00\",\"budget\":\"$52,000,000.00\",\"runtime\":\"2h 15m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Bumblebee\",\"banner\":\"poster_bumblebee.webp\",\"released_year\":\"2018\",\"review_score\":65,\"overview_en\":\"On the run in the year 1987, Bumblebee finds refuge in a junkyard in a small Californian beach town. Charlie, on the cusp of turning 18 and trying to find her place in the world, discovers Bumblebee, battle-scarred and broken. When Charlie revives him, she quickly learns this is no ordinary yellow VW bug.\",\"overview_id\":null,\"revenue\":\"$465,895,025.00\",\"budget\":\"$135,000,000.00\",\"runtime\":\"1h 54m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Cold Pursuit\",\"banner\":\"poster_cold_persuit.webp\",\"released_year\":\"2019\",\"review_score\":54,\"overview_en\":\"The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.\",\"overview_id\":null,\"revenue\":\"$59,213,931.00\",\"budget\":\"$60,000,000.00\",\"runtime\":\"1h 59m\",\"original_language_en\":\"Inggris\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Creed II\",\"banner\":\"poster_creed.webp\",\"released_year\":\"2018\",\"review_score\":67,\"overview_en\":\"Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.\",\"overview_id\":null,\"revenue\":\"$137,944,327.00\",\"budget\":\"$50,000,000.00\",\"runtime\":\"2h 10m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Deadpool 2\",\"banner\":\"poster_deadpool.webp\",\"released_year\":\"2018\",\"review_score\":75,\"overview_en\":\"Wisecracking mercenary Deadpool battles the evil and powerful Cable and other bad guys to save a boy's life.\",\"overview_id\":\"Film action berjudul “Deadpool” ini merupakan film yang bercerita tentang seorang pria yang berprofesi sebagai tentara bayaran bernama Wade Wilson ( Ryan Reynolds ). Wade yang sedang mengalami sakit sekarat karena kanker dan mendapatkan sebuah tawaran untuk percobaan perubahan genetik di mana bisa mengubah dirinya dan menjadi seorang superhero. Karena penyakit yang dideritanya, Wade akhirnya memutuskan untuk melakukan percobaan tersebut. Percobaan tersebut akhirnya membuat diri Wade berubah drastis dan menjadi seorang superhero yang dijuluki dengan Deadpool.\",\"revenue\":\"$741,547,413.00\",\"budget\":\"$110,000,000.00\",\"runtime\":\"2h 1m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Dragon Ball Super: Broly\",\"banner\":\"poster_dragonball.webp\",\"released_year\":\"2018\",\"review_score\":74,\"overview_en\":\"Earth is peaceful following the Tournament of Power. Realizing that the universes still hold many more strong people yet to see, Goku spends all his days training to reach even greater heights. Then one day, Goku and Vegeta are faced by a Saiyan called 'Broly' who they've never seen before. The Saiyans were supposed to have been almost completely wiped out in the destruction of Planet Vegeta, so what's this one doing on Earth? This encounter between the three Saiyans who have followed completely different destinies turns into a stupendous battle, with even Frieza (back from Hell) getting caught up in the mix.\",\"overview_id\":null,\"revenue\":\"$114,102,821.00\",\"budget\":\"$1,000,000.00\",\"runtime\":\"1h 41m\",\"original_language_en\":\"Japanese\",\"original_language_id\":\"Bahasa Jepang\"},{\"title\":\"Glass\",\"banner\":\"poster_glass.webp\",\"released_year\":\"2019\",\"review_score\":65,\"overview_en\":\"In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.\",\"overview_id\":null,\"revenue\":\"$246,941,965.00\",\"budget\":\"$20,000,000.00\",\"runtime\":\"2h 9m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"How to Train Your Dragon: The Hidden World\",\"banner\":\"poster_dragon.webp\",\"released_year\":\"2019\",\"review_score\":76,\"overview_en\":\"As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.\",\"overview_id\":\"Ketika Hiccup memenuhi mimpinya untuk menciptakan utopia naga yang damai, penemuan Toothless 'dari pasangan yang tak teruji dan sukar ditangkap membuat Night Fury menjauh. Ketika bahaya meningkat di rumah dan pemerintahan Hiccup sebagai kepala desa diuji, baik naga dan pengendara harus membuat keputusan yang mustahil untuk menyelamatkan jenis mereka.\",\"revenue\":\"$517,526,875.00\",\"budget\":\"$129,000,000.00\",\"runtime\":\"1h 44m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Hunter Killer\",\"banner\":\"poster_hunterkiller.webp\",\"released_year\":\"2018\",\"review_score\":63,\"overview_en\":\"Captain Glass of the USS Arkansas discovers that a coup d'état is taking place in Russia, so he and his crew join an elite group working on the ground to prevent a war.\",\"overview_id\":null,\"revenue\":\"-\",\"budget\":\"-\",\"runtime\":\"2h 2m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Mary Poppins Returns\",\"banner\":\"poster_marrypopins.webp\",\"released_year\":\"2018\",\"review_score\":66,\"overview_en\":\"In Depression-era London, a now-grown Jane and Michael Banks, along with Michael's three children, are visited by the enigmatic Mary Poppins following a personal loss. Through her unique magical skills, and with the aid of her friend Jack, she helps the family rediscover the joy and wonder missing in their lives.\",\"overview_id\":null,\"revenue\":\"$348,807,090.00\",\"budget\":\"$130,000,000.00\",\"runtime\":\"2h 11m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Mary Queen of Scots\",\"banner\":\"poster_marry_queen.webp\",\"released_year\":\"2018\",\"review_score\":66,\"overview_en\":\"In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.\",\"overview_id\":null,\"revenue\":\"$37,807,625.00\",\"budget\":\"$25,000,000.00\",\"runtime\":\"2h 4m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Master Z: Ip Man Legacy\",\"banner\":\"poster_master_z.webp\",\"released_year\":\"2018\",\"review_score\":52,\"overview_en\":\"After being defeated by Ip Man, Cheung Tin Chi is attempting to keep a low profile. While going about his business, he gets into a fight with a foreigner by the name of Davidson, who is a big boss behind the bar district. Tin Chi fights hard with Wing Chun and earns respect.\",\"overview_id\":null,\"revenue\":\"-\",\"budget\":\"-\",\"runtime\":\"1h 47m\",\"original_language_en\":\"Cantonese\",\"original_language_id\":\"Bahasa China Kanton\"},{\"title\":\"Mortal Engines\",\"banner\":\"poster_mortalengine.webp\",\"released_year\":\"2018\",\"review_score\":66,\"overview_en\":\"Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.\",\"overview_id\":null,\"revenue\":\"$104,236,467.00\",\"budget\":\"$100,000,000.00\",\"runtime\":\"2h 9m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Overlord\",\"banner\":\"poster_overlord.webp\",\"released_year\":\"2018\",\"review_score\":66,\"overview_en\":\"France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.\",\"overview_id\":null,\"revenue\":\"$41,657,844.00\",\"budget\":\"$38,000,000.00\",\"runtime\":\"1h 50m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Preman Pensiun\",\"banner\":\"poster_preman.webp\",\"released_year\":\"2019\",\"review_score\":66,\"overview_en\":null,\"overview_id\":\"Gobang kembali ke Bandung, mengundang teman-temannya, para mantan preman untuk bertemu kembali. Mereka yang diundang adalah Ujang, Murad, Pipit, Cecep, Bohim, Mang Uu, Dikdik dan Joni. Dikdik kemudian tidak jadi datang karena masalah dengan istrinya. Dalam pertemuan yang disebut “reuni kecil-kecilan” itu, Gobang meminta bantuan teman-temannya untuk melakukan penyelidikan, siapa pelaku pembantaian terhadap adik iparnya hingga tewas. Pelaku pembantaian kemudian diketahui adalah Darman dan kawan-kawan yang hanya sebagai orang suruhan. Dalang di balik kejadian itu ternyata teman mereka sendiri, sesama bekas anak buah Muslihat (Epy Kusnandar) yang sebenarnya juga sudah pensiun. Muslihat mencoba mencegah terjadinya perang saudara, tapi Gobang sudah terlanjur datang dengan membawa dendam, “darah dibayar darah, nyawa dibayar nyawa\",\"revenue\":\"-\",\"budget\":\"-\",\"runtime\":\"1h 34m\",\"original_language_en\":\"Indonesian\",\"original_language_id\":\"Bahasa Indonesia\"},{\"title\":\"Ralph Breaks the Internet\",\"banner\":\"poster_ralph.webp\",\"released_year\":\"2018\",\"review_score\":72,\"overview_en\":\"Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, \\\"Sugar Rush.\\\" In way over their heads, Ralph and Vanellope rely on the citizens of the internet -- the netizens -- to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.\",\"overview_id\":null,\"revenue\":\"$529,221,154.00\",\"budget\":\"$175,000,000.00\",\"runtime\":\"1h 52m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Robin Hood\",\"banner\":\"poster_robinhood.webp\",\"released_year\":\"2018\",\"review_score\":58,\"overview_en\":\"A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.\",\"overview_id\":null,\"revenue\":\"$73,260,114.00\",\"budget\":\"$100,000,000.00\",\"runtime\":\"1h 56m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Serenity\",\"banner\":\"poster_serenity.webp\",\"released_year\":\"2019\",\"review_score\":51,\"overview_en\":\"Baker Dill is a fishing boat captain leading tours off a tranquil, tropical enclave called Plymouth Island. His quiet life is shattered, however, when his ex-wife Karen tracks him down with a desperate plea for help.\",\"overview_id\":null,\"revenue\":\"$8,547,045.00\",\"budget\":\"$25,000,000.00\",\"runtime\":\"1h 46m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Spider-Man: Into the Spider-Verse\",\"banner\":\"poster_spiderman.webp\",\"released_year\":\"2018\",\"review_score\":84,\"overview_en\":\"Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \\\"Kingpin\\\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.\",\"overview_id\":null,\"revenue\":\"$375,450,417.00\",\"budget\":\"$90,000,000.00\",\"runtime\":\"1h 57m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"T-34\",\"banner\":\"poster_t34.webp\",\"released_year\":\"2018\",\"review_score\":49,\"overview_en\":\"In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.\",\"overview_id\":null,\"revenue\":\"-\",\"budget\":\"$10,000,000.00\",\"runtime\":\"2h 19m\",\"original_language_en\":\"Russian\",\"original_language_id\":\"Bahasa Rusia\"},{\"title\":\"The Girl in the Spider's Web\",\"banner\":\"poster_thegirl.webp\",\"released_year\":\"2018\",\"review_score\":60,\"overview_en\":\"In Stockholm, Sweden, hacker Lisbeth Salander is hired by Frans Balder, a computer engineer, to retrieve a program that he believes it is too dangerous to exist.\",\"overview_id\":null,\"revenue\":\"$17,894,345.00\",\"budget\":\"$43,000,000.00\",\"runtime\":\"1h 57m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"The Mule\",\"banner\":\"poster_themule.webp\",\"released_year\":\"2018\",\"review_score\":65,\"overview_en\":\"Earl Stone, a man in his 80s, is broke, alone, and facing foreclosure of his business when he is offered a job that simply requires him to drive. Easy enough, but, unbeknownst to Earl, he’s just signed on as a drug courier for a Mexican cartel. He does so well that his cargo increases exponentially, and Earl hit the radar of hard-charging DEA agent Colin Bates.\",\"overview_id\":null,\"revenue\":\"$171,304,407.00\",\"budget\":\"$50,000,000.00\",\"runtime\":\"1h 57m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"},{\"title\":\"Venom\",\"banner\":\"poster_venom.webp\",\"released_year\":\"2018\",\"review_score\":66,\"overview_en\":\"Investigative journalist Eddie Brock attempts a comeback following a scandal, but accidentally becomes the host of Venom, a violent, super powerful alien symbiote. Soon, he must rely on his newfound powers to protect the world from a shadowy organization looking for a symbiote of their own.\",\"overview_id\":\"Seorang jurnalis, Eddie Brock (Tom Hardy) ingin melakukan sebuah investasi kasus terhadap penemuan yang dipimpin oleh Dr. Carlton Drake (Riz Ahmed). Karena investigasinya, Eddie mengunjungi lab milik Dr. Calrlton Drake. Semuanya ditujukan untuk membuktikan bahwa Dr. Carlton Drake sedang sedang melakukan tindakan jahat menggunakan Symbiote. Namun, sial bagi Eddie, organisme hidup ini masuk ke dalam tubuhnya. Eddie menemukan kekuatan super dalam dirinya yang mampu mengendalikan semua hal yang ia lakukan.\",\"revenue\":\"$855,013,954.00\",\"budget\":\"$116,000,000.00\",\"runtime\":\"1h 52m\",\"original_language_en\":\"English\",\"original_language_id\":\"Bahasa Inggris\"}]";

    public static List<Movie> getSeedData() {
        try {
            JSONArray rawSeedData = new JSONArray(seedData);
            return extractMoviesFromJSONArray(rawSeedData);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static List<Movie> extractMoviesFromJSONArray(JSONArray rawSeedData) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int ii = 0; ii < rawSeedData.length(); ii++) {
            JSONObject rawMovie = rawSeedData.getJSONObject(ii);
            Movie movie = extractMovieFromJSONObject(rawMovie);
            movies.add(movie);
        }

        return movies;
    }

    private static Movie extractMovieFromJSONObject(JSONObject rawMovie) throws JSONException {
        Movie movie = new Movie();
        movie.setTitle(rawMovie.getString("title"));
        movie.setBanner(rawMovie.getString("banner"));
        movie.setReleasedYear(rawMovie.getString("released_year"));
        movie.setReviewScore(rawMovie.getInt("review_score"));
        movie.setOverviewEn(rawMovie.getString("overview_en"));
        movie.setOverviewId(rawMovie.getString("overview_id"));
        movie.setRevenue(rawMovie.getString("revenue"));
        movie.setBudget(rawMovie.getString("budget"));
        movie.setRuntime(rawMovie.getString("runtime"));
        movie.setOriginalLanguageEn(rawMovie.getString("original_language_en"));
        movie.setOriginalLanguageId(rawMovie.getString("original_language_id"));

        return movie;
    }
}