package hackerrank.booking.com.backendcodesprint;

import java.util.*;

/**
 * Created by andrew on 22/09/16.
 */

public class Reviews {
    static int getScore(long time, int reviewLength) {
        int point = 0;
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, Calendar.JUNE);
        cal.set(Calendar.DAY_OF_MONTH, 15);
        long startT = cal.getTimeInMillis() / 1000;
        cal.set(Calendar.MONTH, Calendar.JULY);
        long endT = cal.getTimeInMillis() / 1000;
        if (time >= startT && time < endT) {
            point = point + 20;
        } else {
            point = point + 10;
        }
        if (reviewLength >= 100) {
            point = point + 20;
        } else {
            point = point + 10;
        }
        return point;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Map<String, Map<Integer, Integer>> reviewAndScore = new LinkedHashMap<String, Map<Integer, Integer>>();
        Scanner in = new Scanner(System.in);
        int numP = in.nextInt();
        int numR = in.nextInt();
        in.nextLine();
        //init the statistics
        for (int i = 0; i < numP; i++) {
            //passion-->reviewid:score
            reviewAndScore.put(in.nextLine().toLowerCase(), new HashMap<Integer, Integer>());
        }
        //collect stats for eah passion and each reviewer
        for (int i = 0; i < numR; i++) {//each review
            int rId = in.nextInt();
            long time = in.nextLong();
            in.nextLine();
            String review = in.nextLine().toLowerCase();
            //System.out.println(review);
            for (String p : reviewAndScore.keySet()) {//this review might be for multiple passions
                if (review.indexOf(p) >= 0) {//review is about it
                    if (reviewAndScore.get(p).get(rId) == null) {
                        reviewAndScore.get(p).put(rId, getScore(time, review.length()));
                    } else {
                        reviewAndScore.get(p).put(rId, reviewAndScore.get(p).get(rId) + getScore(time, review.length()));
                    }
                }
            }
        }
        in.close();
        //print result
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<Map.Entry<Integer, Integer>>(numP * numR, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
                if (e2.getValue() == e1.getValue()) {
                    return e1.getKey() - e2.getKey();//choose one with smaller id
                } else {
                    return e2.getValue() - e1.getValue();//graeter score appears front
                }
            }
        });
        //print result by passions
        for (String p : reviewAndScore.keySet()) {
            if (reviewAndScore.get(p).size() == 0) {
                System.out.println(-1);
            } else {
                pq.clear();
                //for all reviews for a passion, sort them
                for (Map.Entry<Integer, Integer> e : reviewAndScore.get(p).entrySet()) {
                    pq.add(e);
                }
                //pick the one with max score
                System.out.println(pq.poll().getKey());
            }
        }
    }
}
/*
input
18 2000
montmartre
cruise
dinner
culture
atmosphere
champs
weather
versailles
sacre
experience
cafes
seine
sights
museums
tour
restaurants
louvre
eiffel
19  1468356697
paris is busy (&amp; hot in the summer!) if you learn to stay away from touristy spots at peek times you&#39;ll love it even more many parisians speak english also they aren&#39;t good at picking up their dog poop so just watch where you step most cafes and restaurants have excellent food  enjoy it don&#39;t forget to slow down and sip some rose&#39; and watch people go by get to saint martin canals and watch the boats go through the locks don&#39;t miss st chapelle stained glass windows - they are just beautiful try to catch a vivaldi concert there (or in luxembourg neighborhood) don&#39;t try to see everything in the louvre at one visit; - it won&#39;t happen and you&#39;ll just be exhausted pre-book tours&#47;museums - you&#39;ll be glad you did enter the louvre from the side in the park - downstairs - there is a lot less waiting in line go on a bike tour! at restaurants (tap) water is free (&amp; drinkable) so it&#39;s not necessary to order bottled water for $6 do not engage with the girls who walk up to you and ask &quot;do you speak english?&quot; they are thieves keep walking (usually found at eiffel tour &amp; notre dame) toss some change to the street&#47;metro musicians palace of versailles is way too crowded -try to catch on a weekday and be the first in the door buy a carnet (10 metro&#47;bus tickets) for less than individual tickets or get a paris pass for several days of sight seeing which includes monuments and transportation options (bus&#47;metro&#47;rer) if your party is 3 or more - take a taxi from the airports and split it - for speed &amp; convenience
36  1468357173
we enjoyed eating at the hidden restaurants that the locals go to getting some cheese baguette and wine and sitting in a park we also did the usual touristy things but loved the easier going options too
12  1468357203
i like the location the most but i did not like the hotel at all!  and the receptionists were not helpful they put us in a very small room we had to get angry at them until they gave us another room!!! Ã°Å¸ËœÅ¸Ã°Å¸ËœÂ®Ã°Å¸ËœÂ®Ã¢ËœÂ¹Ã¯Â¸Â
37  1468358302
the food at every cafe and restaurant was very good we stayed very near the arc de triomphe at elysees hotel and it was awesome we used the big bus tour to see as all the major attractions
35  1468361293
just travelling through paris really so did not have much time walking between railway stations i took in some of the sights which were impressive would like to go back to investigate the city in more detail
85  1468361563
love the city the vity of love
23  1468362620
you can&#39;t visit paris without eating macaron from ladure and visiting the moulin rouge it&#39;s fabulous
59  1468364835
paris stores close at 7:00 pm and champ lesee may be 2 hr late but still too early to enjoy the nice weather     transportation is  relatively  expensive and food as well  the good issue is you will find most of sales people at stores they speak english
52  1468365845
had great times visiting opera garnier the eiffel tower and the catacombs all made so much easier by excellent public transport links   we also walked a great deal and saw so much more of this stunningly beautiful city   numerous very good quality restaurants and bars abound which enable you to sample pretty much any cuisine you fancy
63  1468382167
don&#39;t forget to visit versailles worth the slighlty long trip buy the paris pass as it has lots of benefits the big bus should be done for two days if you want to visit all the stops   the metro line are a bit more confusing but you&#39;ll figure it out eventually  lastly paris is expensive so ready your budget
75  1468385486
too much to see  i loved paris!
96  1468387882
don&#39;t think just go to paris!
13  1468392603
i didnt have time to explore i will have to come back
33  1468394490
totally wonderful in every way hotel was in in great location for travelling to all of the sights we wished to visit with plenty of choice in local bars and restaurants
19  1468394892
loved the world war ii walking tour and the paris secrets and charms tour we did a different insight into paris
85  1468396798
shopping when the sales are on last week of june as genuine first and then second reductions a week later  good food  lovely to just stroll about    rer high speed metro lines wonderful to whizz across paris quickly and into the suburbs
67  1468398066
as this was a trip to visit our daughter we didn&#39;t do the usual sightseeing trips went to a lovely evening show at the place de les invalides travelled from paris to vernon and then a shuttle bus to giverny beautiful village which was the home of claude monet visited his house and gardens well worth a visit went to a lovely restaurant in l&#39;auteil district - le mouton blanc would advise the Ã¢â€šÂ¬32 menu excellent choices and friendly staff
36  1468398495
very easy to get around - i walked everywhere!i would recommend learning the language before you go  i wish i knew a bit more as it was often hard to find locals willing to speak in english
14  1468399167
i was in town for a springsteen show so didnt go sight seeing paris in general is very expensive and not the cleanest of cities
12  1468401855
you cannot expect help from people and staff from any place  when we went to buy some train tickets the help was very minimal as we didn&#39;t speak french if traveling to paris be prepared with your own maps before hand everything written in french
41  1468405556
notre dame-stunning and breathtaking
40  1468406283
great fun
96  1468406709
the great sights are well known the metro system is excellent and easy to navigate single trip t1 tickets are cheap at Ã¢â€šÂ¬180 and include metro rer trains and buses of course the food and drink are excellent but be prepared to pay heavily - a coffee can cost Ã¢â€šÂ¬4
74  1468409119
a beautiful city with lots of history and art everywhere
46  1468410433
i love paris i want to live there  bu just double check the weather &amp; sporting events that may be on before you go
46  1468410938
give more time for your visit as there are al lot of things to do and see good luck
48  1468411241
i was in paris as a solo traveller for three full days and i saw everything i wanted to including:  - eiffel tower  - montmartre  - pantheon  - champs- elysees  - palace of versailles  - louvre  - two love lock bridges  - notre dame  - catacombs of paris  - moulin rouge   - and so much more    i walked everything as it is either in close proximity or in walking distance i really didnt think i was going to like it as much as i do and am so happy that i chose to go there! definitely recommend this place for something outside the norm!
64  1468414128
was a lovely looking clean and friendly city however very expensive!!
98  1468417729
culturally rich historically layered wonderful city not to be missed in one&#39;s lifetime
37  1468418098
stay on a hotel or place near the city to really maximize your time
27  1468418277
this visit to paris was the sixth time do to we visit to our daughters who live there  paris is a city that you never finish to visit very attractive places  walking through the city always you will find new attractions and beautiful places
31  1468419367
all paris
12  1468421589
definitely a romantic atmosphere and plenty of stuff to see visit the lourve! just make sure you&#39;re careful with your belongings and cash ignore anyone trying to push a piece of cardboard at you
6   1468421669
after spending time in saumurmuch prefer saumursmaller town less busy  but if you haven&#39;t seen paris
63  1468428188
very exspensive to drink
73  1468428407
the river and the monuments
90  1468428760
so much to see so much history so much culture
87  1468429594
easy to walk around take the metro or buses or cycle on the city bikes most of churches are entry fee easy to plan by areas i liked to get lost around the streets as the buildings have a lot to show
12  1468438944
had lunch at nossa charrusqueria which was great  lovely food and great service  not very expensive
87  1468439546
great friendly
63  1468439794
great atmosphere soccer finals
95  1468443554
paris is paris everyone has to go to paris once in life
38  1468449231
the architecture history and sites are awe-inspiring found the people very friendly and welcoming and even without asking they spontaneously volunteered to help me with my disablilty needs the downside was the lack of food options in french cuisine for vegetarians as most dishes seemed to contain some meat fish or eggs; indian or italian were the only options visit to the louvre night time boat trip on the siene and eiffel tower can be repeated over and over again
78  1468453398
i was only there for 2 nights so barely had time to see anything i did however get to see st catherine laboure at 140 rue du bac there in parisincredible     i was astounded by the massive number of people that smoke in paris i&#39;ve never seen so many smokers in one place  come on folksquit smoking already  the secondhand smoke was very hard to escapesidewalks people passing by restaurants such a shame and a major pollutant
54  1468456446
history art and culture at every turn and over every shoulder more poverty than we expected but it was an incredible experience and so much to see leave yourself enough time!
40  1468467376
very very beautiful city so much to see and do excellent food and wine very walkable!we walked from the lourve to the opera to our hotel by the eiffel tower in one evening
62  1468471940
a self catering apartment in an area like montmatre lets you experience the vitality of paris  so many well known places to see  loads of cafes and restaurants that provide great meals  the metro is easy to manage  a hop-on hop-off bus is a great way to orientate yourself to the city  easy walking city
36  1468472076
paris is such a wonderful city that caters for the needs of a variety of tourists  we felt super safe in all parts of paris so don&#39;t get stressed out with the extra security  they are there to protect you
57  1468472233
simply paris!
19  1468473211
easy to travel around liked the bus&#47;batteau trips
20  1468475692
i been here several times  still like it a lot every time you will find surprise and meet nice people
28  1468475855
paris is wonderful and the weather was great for our stay euro 16 made it a bit more crowded than we expected but it was a very vibrant atmosphere
30  1468477748
loved the art and architecture paris is a lovely city and it&#39;s nice to see the old restored and cared for instead of bring pulled down
97  1468480941
purpose was to go to the euros 2016 but visited les invalides and thoroughly enjoyed the museum particularly the exhibition on napoleon also spend a great day at versailles whose gardens are particularly spectacular    did not paris to be too expensive as long as you stay away from the tourist highspots    great city to visit and also fairly compact with super metro system to get around
38  1468482976
nice city but a mess near the east and nord station
22  1468482998
loved the history and architecture locals were friendly and helpful and thankfully for us most could speak some english public transport was excellent particularly if you have a visite pass which is valid on all public transport within relative zones  if walking is difficult i suggest using buses and not metro lifts and escalators are limited and there are lots of stairs  food and drink in cafes are reasonably priced and alfresco good to people watch      if you want to make full use of city&#39;s attractions a paris pass is ideal  we purchased for four days and recieved 4 day paris pass 4 day museum pass and 5 day visite pass which for best value use independently because once each is validated must be used consecutively    one thing that shocked me initially was the amount of homlessness people living on footpaths doorways and tents all ages men women and children and the general acceptance by authority and people generally
52  1468484109
i have been to paris many times but on this occasion it was fantastic it was clean and friendly i dont think there is a better laid out city great for walking around every corner there is a new place to discover to capture the atmosphere looking forward to my next visit
93  1468484827
very dirty overflowing trash bin  and many beggars on the streets area around eiffel tower filthy unmown grass very unkempt
32  1468486025
absolutely gorgeous city with so much to do and see! we can&#39;t wait to go back!
55  1468487778
great city lot of things to do good cafe and restaurants
2   1468488754
busy metropolitan city with history at every turn
31  1468488902
i love walking the streets of paris - you get a great feel for the city esp recommend the left bank from notre dame up to the rue des ecoles then circle back to metro station st michel (nice streets lots of shops) also look for the covered arcades in the area near lafayette gallery in the suburbs below montmatre stop in at one of the many cafes for an espresso!     for sites the louvre and the eiffel tower are always worth a visit; look at notre dame from the back for a great view; go to the musee rodin for a quiet retreat from busy life - lovely gardens with statues musee d&#39;orsay is great for impressionist paintings    for food - the left bank has lots of cheapish bars and restaurants with decent food lunch and dinner recommended here
49  1468489189
great city but they do charge! we paid 60 euros for a pizza and got charged 40 euros each for a 15 minute tuk tuk ride we drove in but id recommend you take the eurostardriving itself wasn&#39;t too bad but being charged on every road you go on gets a bit annoying apart from the cost the city is amazing clean and full of love!
58  1468489430
bike tour take that!
68  1468489516
paris wonderful city of lightgood persons and culturesbut everyone is busyno time spare 5min
5   1468490384
architecture
15  1468490954
this time we visited lesser known places (jewish quarter) and smaller parks such as bercy
41  1468493447
i found what i expected
4   1468493796
we visited notre dame cathedral chapelle royal pompidou centre the louvre monmartre jardin du luxemburg  enjoyed a stroll each evening along the seine watching the river boats loaded with tourists  loved the pmpidou and luxemnpburg gardens  louvre very noisy and crowded but better in less popular areas  eventually found little supermarkets tucked into side streets where we bought provisions for a light meal and partial breakfast so bought just one main meal out  saved a lot of money !tip - get out and about early parisians are not early risers so you can beat the crowds  museum tickets on line also good investment
96  1468495136
paris has become very criminal and smelly
26  1468495183
weather food night life in champs-elysees street shopping
81  1468495934
best city in the world and one of the best views is from the top of l&#39;arc de triomphe to really take in majesty of the long boulevards  good bars and restaurants in all districts and if you want something special search viamichelin  fastest (although not the cleanest) way by far to get across the city is the rer then connect to metro or walk to final destination   best way get across the city (imo) is by bike using velib
28  1468496540
great place for city walks with lots of great shops museums cafes and wonderful food you need at least 3 days
2   1468497137
having been to paris many times i still enjoy walking around and discovering little eateries or some wall art tucked away the museums are always a must but don&#39;t forget to include the smaller more intimate ones!
42  1468500064
paris is a beautiful city and easy to manage even with younger children however the queues at the eiffel tower were very long so didn&#39;t manage to go to the top
80  1468500082
i adore paris everything is amazing notre dame musÃƒÂ©e du louvre and opera are must to see also eiffel tower and the dinner at the eiffel restaurant i also like to bateau bus tour la basilique du  sacrÃƒÂ© coeur shopping in champs elysÃƒÂ©es visiting the arc de triomphe and musÃƒÂ©e de l&#39;art moderne centre pompidou place de la concorde et sa roue gallerie lafayette et printemps for shopping  it is very easy to go outside paris and visit the chateau de versailles it is very important place to visit  in one word paris is a city to visit  multiple times
50  1468500675
eiffel tower is good
25  1468501676
a wonderful city so very french
17  1468501724
c
95  1468502047
the sites cafes and restaurants and good train&#47;metro system ate at a cafÃƒÂ© on the bastille roundabout  favourite places are notre dame walking along the seine latin quarter sacre couer and the arc de triomphe&#47; champs elyses
11  1468502328
paris is a lovely city albeit very dirty and full of graffiti and beggars
14  1468502359
loads to enjoy whatever you like - culture shopping history dining etc some great ideas of what&#39;s available on pinterest
59  1468502625
a great location easy to get around on foot loved it and will make a return visit soon
1   1468502752
great parks and monuments the only down side is everything is in french and everyone speaks french disappointed that for such a global city they should integrate english in their culture
25  1468502853
we liked visiting the sights of paris and the restaurants  uber drivers were excellent and very good value  got food poisoning from a steak tartare (turned quickly on the pan) and regret taking the chance otherwise food was good and varied
2   1468502950
great sights
61  1468504743
beautiful and magical  very busy though
38  1468504814
hippopotamis was good arc de triomphe eiffel sacre coeur take on&amp;off bus tour notre dame
88  1468504971
location of hotel
11  1468505190
love the diversity of activities one can do in paris transports work overall well too
26  1468505456
loved paris so much could&#39;ve stayed forever was very happily surprised to see that 18-25 year old members of the eu get lots of free entry to museums and so forth however next time i think i&#39;ll buy passes earlier so that the queueing is as minimal as possible
13  1468505514
eiffel tower was closed major disappointment as it was the reason we went to paris for the last day of our 3 weeks in france if i had known i would have gone to disney but ended up missing out on both
55  1468505640
too much litter in streets but architecture was stunning
48  1468506500
near the seine a walk from the eiffel tower and statute of liberty !!one or to decent places toeat and drinking ok very residential a few convenience shops around and about
58  1468506699
the metro is great once you get over initial uncertainty by far the best way to travel throughout the city in general but especially you have or want to fit a lot into a short time
78  1468506723
this was my 5th time to paris and i try not so see &amp; do everything but take it a bit slower there is just too much going on to try to do all of it  before going i make a list of what i want to see &amp; do then i prioritize it so what is most important is not missed museums close early so i leave boat rides for the bend of the day public transportation (especially the metro) is easy and very inexpensive once you get the hang of it is the fasted way to move around
65  1468506752
paris is a living museum every corner has a story love the architecture    easy use of metro &#47; rer &#47; bus with excellent connectivity     love the gastronomic choices we had greek french vietnamese and chinese food and watch the people go by    visited the museums places of interests monuments etc and cruised the seine plenty of parks to relax     unfortunately was careless and lost my phone to a pickpocket at les halles metro
56  1468507323
busy and expensive but so pretty
85  1468507512
remains a beautiful city and great restaurants - try lunchtime specials at the michelin ones they are often better value than the bistros downsides are it has become a very very expensive city and the constant security is an unpleasant but necessary sign of the times
74  1468507915
food can be expensive especially breakfast also careful with pickpockets
37  1468508532
great city - to walk to soak in atmosphere bars &amp; boulevards to visit great museums of the world to see great buildings and views from sacre coeur to notre dame and st supplice and ste chapelle to champs eysee and shops the latin quarter and great restaurants plus the river seine - day or night
67  1461172045
always nice to visit paris
33  1461172761
beautiful city especially in fine weather do your homework on where to eat though not everywhere is good these days easy to walk around and relax in cafÃƒÂ©s and bars but can be pretty expensive
40  1461174214
the city of beauty romance and elegance
26  1461177065
clean city lots to see and do great to be part of &#39;cafe culture&#39;
93  1461177159
the sightseeing places were great and the foods were great  but the city itself was dirty and smelt like urine everywhere  the metro system connects the city very well but no elevators anywhere and the transfer was horrible
62  1461178024
because we don&#39;t understand french it felt as if you were afraid to wander off the beaten track
8   1461180857
i love the decent way french women are dressed  it&#39;s nothing about the luxurious brand shops by the streets sell lovely stuff
97  1461181859
the open tour bus was great as we could hop on and off to view sites of interest     notre dame was absolutely amazing to view
40  1461183843
it&#39;s paris city of food fashion and historywhat more can you want!!!
46  1461186963
paris is paris the city of love but one thing a lot of people asking money no much cleanness on the streets some restaurants not very polite with tourists one thing that i see since last time i&#39;ve been in paris  they speak more englishmetro very dirty and with a lots of drunk people (people without a roof) one of worsts that i saw in europe
92  1461189194
loved paris first time there just 4 days and i could have stayed longer for sure   love walking so did not get any metro and got to see great things   loved the al fresco terraces and the &quot;people watching&quot;  great atmosphere and could have another glass of champagne at the top of the eiffel tower with amazing views     in love!
55  1461191747
paris is vast and complex and delightfully easy to get around   buying a carnet of tickets on your first day is probably the best purchase you&#39;ll make since tickets work for rer&#47;sncf trains the metro and the bus  my absolute highlights all were around the notre dame area leave a day to explore it thoroughly make sure you don&#39;t miss a stroll through the marche aux fleurs which is absolutely a dream the two islands are absolute gems with charming shops and restaurants on every street   as for the notre dame itself go in time for an evening mass that way you don&#39;t have a big queue to get in but it&#39;s still amazingly atmospheric you also get to see it all lit up if you stay around that area as dusk sets in which is a totally spectacular sight around the back there&#39;s a charming park and a bridge that offers lovely views (and often a busker or singer which only adds to it)  the eiffel tower is another essential though only by night explore the surrounding area and go for a stroll by the seine during the day and come back to the tower to watch the sun set and the lights come on  if youre interested dont get scared off by the huge lines outside the georges pompidou those are just lines to get through security and move really quickly once inside you can get your tickets from an automatic machine or from a person at a counter neither of which have much of a line  on a sunny day the jardin du luxembourg is absolutely gorgeous as is the jardin des plantes i really highly recommend both the jardin des plantes is a wonderful mish-mash of gardens a small but really great zoo and some small museums the jardin du luxembourg is more formal with a spectacular fountain in the central area where you can rent little boats to play with    last but not least allow yourself to wander the city with such brilliant public transport its tempting to zip from one place to another but if you do that you miss the city entirely the number one highlight of my trip was sitting down on a wall by the seine eating a sandwich i had bought from a bakery just down the road paris seems very fast paced but that just means that when you take time to stroll around and explore its even more special
89  1461194848
i have been to paris several times and it never fails to enchant  i love to look up and see the eiffel tower (even if i dont particularly want to go up it again) to walk alongside the seine and over the bridges it doesn&#39;t feel like paris until you have walked by the river  highlights of the trip were the rodin museum and the discover walk around montmartre i usually feel sorry for people when i see them on guided tours following someone with a placard but this tour was informal entertaining and we learnt so much! i need to go back to do the tours of notre dame the latin quarter and the marais! we also visited the market on the rue mouffetard and moochec around the latin quarter and the rue du bac  we ate breakfast at the cafe st placide (rue de rennes) lunch of sandwiches from the bakery or (before the tour) in a lovely bar&#47;creperie just by the deux anes theatre (metro blanche) and evening meals at the cafe de mars cote bergamote and la rotonde all different but good in their own ways  the metro is simple and cheap and saved our tired legs-giving us chance to see and do more!
74  1461222558
hard to find good restaurants  great places to see - lived sacre coeur  great shopping at des halles  luxembourg gardens beautiful
8   1461222782
if you planning go to paris try it during week otherwise everywhere is so long quebe   no chance get in louvre museum probably it is need to be booked online maybe that could help
12  1461224119
fantastic city two days was not enough easy transport lovely architecture
61  1461224777
parisians are generally rude especially shop staff at zara don&#39;t honeymoon there you&#39;re much better off somewhere relaxing and where the people are nicer
19  1461224798
we had an incredible side of beef at la maison de l&#39;aubrac with some really nice wine it wasn&#39;t cheap but it was worth every penny a real highlight and i would recommend it to any couple that enjoys food
65  1461225899
romantic
95  1461230453
jaquemart andreluxembourg gardens  cluny museum
25  1461230679
aeroville mall beside airport  tower eiffel  rue de revouli  forum des halles
28  1461232173
paris is the most beautiful city i have ever seen
10  1461232631
paris is a great city to visit many attractions to see friendly people and nice food!    check out my blog for more about paris:     https:&#47;&#47;dinakhaleelwordpresscom&#47;2016&#47;04&#47;15&#47;touring-the-city-of-love&#47;
10  1461233271
paris is gorgeous  make sure you get out of the city centre sometimes too dont forget versailles just make sure you check out public transport before you travel to make life easier than working it out on the spot lol
42  1461233487
being musicians and staying in the music area added a fantastic edge to our whole family as we could play the different instruments on many musical shops close to all thr highlights this apartment was the best in our travel from belgium italy to france
73  1461234847
paris was everything we expected  we enjoyed our time here
79  1461236073
a lovely city rich culture ideal for relaxing romance sightseeing and shopping
53  1461236735
do the day and night bus tour with big bus!
89  1461236870
pretty pricey and i didn&#39;t find the city to be too interesting  you won&#39;t find good food everywhere
98  1461237159
paris city is beautiful nice for couples
25  1461238642
nice place to visit
33  1461239525
we enjoyed walking and taking in the sights we found our way around ok with a map!we chose not to use the underground we went to angelinas for morning teavery delicious quite expensive but worth it for the experience this was our second visit and it was good to just revisit places and take time to wander rather than rush and try to do everything
62  1461239833
great city stroppy attitude to service
91  1461240242
very busy and lots of tourists and it was off season  we really preferred the smaller cities in france
86  1461242991
saw most tings we wanted to didn&#39;t cue up for some tourist spots and didnt by beat the line tickets happy with what we saw
69  1461245572
did 4 days just right
28  1461245629
es una ciudad preciosa y tiene muchÃƒÂ­simas cosas que ver lo malo es que las distancias son muy grandes y lo que parece que esta cerca en realidad no lo esta aunque el metro funciona perfectamente  lo peor de parÃƒÂ­s es el tema de la comida y bebidas es muy caro los menus es lo mas rentable aunque despuÃƒÂ©s se eleva el coste por las bebidas que tienen un precio desorbitado  un consejo para los viajeros es usar el botabus es una manera perfecta de moverse por parÃƒÂ­s ya que se coge en la torre eiffel y se va parando en diferentes puntos de interÃƒÂ©s donde te puedes subir y bajar cuantas veces quieras a lo largo del dÃƒÂ­a el precio es de 17 euros pero por ser estudiante te sale por 11euros
34  1461247537
the sights
60  1461248552
stayed in paris on an overnight stop on the train and not as a tourist  enjoyed an evening stroll by the seine
26  1461249822
i loved the parisian way of life  soft spoken and polite people who will break into the english language as soon as you start to get flustered   the metro is a breeze  one carnet ( ticket ) can be used to get around ( jumping from one train to another untill you get to your destination ) untill you leave the metro station  buy a book of ten carnets for 14 euros and that will give you 5 two way journeys   great place for food and clothes shopping  the arc de triomphe and champs-ÃƒÂ©lysÃƒÂ©es are 5 minutes walk away from this apartment   the charles de gaulle metro station is right by the arc de triomphe so head there  to start your days adventure  everywhere accepts credit cards
0   1461250131
fantastic should come with my family for longer stay
55  1461251034
my favourite place eiffel tower food a bit problem for us muslim ppls but overall we are happy
78  1461251433
i love the architecture in paris it is just so beautiful
10  1461254164
st ouen markets - &#39;discover antiques and collectibles&#39;  the champs elysees - &#39;upmarket shopping&#39;  sacre couer - &#39;the spectacular sunset&#39;    the metro is easy to use and cheap tickets are available we felt very safe    penta hotel near the airport is perfection if necessary for an early morning&#47; late night flight
59  1461254294
excellent restaurant just up the road called 141 food and staff fantastic
48  1461255823
place to return thousants of activities
63  1461257300
a perfect city for a short break the metro and rer are easy to use and there are a plentiful supply of taxislots of small streetscourtyards and alleyways to explore on footthe marais on the right bank being an interesting area and close to the pompidou center and the restaurant benoiton the left bank close to the notre dame in rue de pontoise is itineraires a restaurant well worth visiting
4   1461257837
highly recommend climbing the 400 steps to the top of notre dame belltowerincredible views also lovely spot for lunch in tuliers gardens on a sunny day
97  1461259370
seine river cruise was amazing we were in big group and had a big boat with food and drinks included very easy to walk around paris if ur staying fairly central
23  1461259934
what can i say that hasn&#39;t already been written or said about the most visited city in the world?
93  1461260224
as we always used the metro my 4 yrs old boy was in trolley asleep as we head to metro  parisians are very caring and helpful
51  1461260262
paris is paris; &#39;nuff saida trip down the seine is a must
55  1461261082
people are rude and they don&#39;t like people that don&#39;t speak french there were homeless people around the corner of every hostel there were hookers all over the henerator hostel the only good thing was the eiffel tower but the lights don&#39;t stay on long and this was never communicated i will not come back
56  1461261267
so much to see!!!!
79  1461262088
the life and cafes steps away on rue montorgueil was the ultimate experience  close to the metro easy access to everything
76  1461263806
the eiffel tower was my favourite part of the trip a definite must see everything else was just as good the city is a place to be so exciting and beautiful i would go back in a heartbeat
48  1461267602
great weather in april food and wine tops expect everything to be on the expensive side  don&#39;t miss   la fermette marbeuf restaurant   food and ambience
47  1461270389
the metro makes travelling around the city easy; but walking is great way to get in touch with paris take your time relax and enjoy watching - and being part of - city life arts science gardens food - and wine! - there&#39;s everything you could wish for in this amazing place
41  1461271004
nice place
56  1461271207
the safety in paris was poor i got my phone stolen by pickpocket on my 15 minutes arrival in paris it was just when the first time i got into metro in paris  public transport here is easy to access to go everywhere but it has a poor safety poor condition because need to climb a lot of stairs and not a good place for disabled person since they don&#39;t provide any disabled facilities    however paris is a good place for branded shopping and good visit for the historical building
28  1461272888
fantastic meal at creperie josseline but forget cupola - over-rated and not very welcoming
94  1461275357
paris a stunning city!
7   1461276506
my favorite city in the entire world
57  1461285785
it was wonderful loved the city
81  1461287683
paris is a great city for a holiday easy to get around by walking or taking the metro beautiful buildings everywhere nice polite people not to mention gorgeous food
37  1461305558
i always enjoy paris with its boulangeries cafes the eiffel the seine and so forth i do always want to come back   however i do not feel comfortable with the cases of pick-pocketing
18  1461310137
city of art and history you can stay there for at least 15 days and enjoy all historical and artistic sites wear a comfortable shoes and take a bottle of water paris worth to visit on foot
74  1461310565
fabulous city!great place to sit and people watch  stunning art in the museums  easy to get around on foot river boat or metro
8   1461312266
it was very sunny during our stay so the trees and river looked very beautiful the metro was quick and efficient but rather dirty and run-down the buses were clean and easy to use we like the museums and the parks and just walking around the interesting areas the museums were quite expensive we particularly like the rodin and marmottan  eating out is expensive which is why we stayed at aparthotel
26  1461313036
amazing city  friendly people and clein  i highly recommend to visit
70  1461314313
paris is unique walk at sajna -river the architecture is amazing
98  1461320403
i think every thing in paris is great except that people don&#39;t talk english
19  1461322289
paris overall is very nice somehow on every corner we saw atleast one pharmacy which was odd there are restourants everywhere but no food stores do they eat only in restourants? for a food market you have to travel by metro where you can find some organic food markets the food from there is unbelievable! overall paris before march isnt really beautiful it is always nice at the night time near popular places there are people that want to get as much money as they can one guy near de louvre museum took a picture of us with an old camera (probably wasnt working) and offered us 2 pictures for 30 euro! dont get fooled that easily and dont buy anything from them other than that enjoy your stay and get most out of it!
32  1461323347
even if you only visit once paris is a must to experience the eiffel tower the louvre shopping in all the difference districts checking out the cute cafes there is so much art music &amp; culture in this beautiful city you won&#39;t ever get through it all but you need to at least check out some of it whether you fly in or catch the eurostar - it is easy to get around just take an umbrella for the cold rainy day but even the wet days are still beautiful in paris
82  1461327865
what can i say that hasn&#39;t already been said?  i&#39;m not a city person by any stretch of the imagination but if i have to visit one paris is at the top of the list  i have been there many times and have always enjoyed my visit    i highly recommend getting a train pass and not the 5 day tickets  depending on your length of stay you can charge it for 1 week (mon - sun) or even a month (1st - eom)  while you have an initial investment for the pass (photo and the actual pass) it&#39;s valid for 10 years and you only have to recharge it as necessary  it&#39;s by far less expensive and you&#39;re less likely to lose it versus the little tickets
15  1461331450
awesome! loved the old buildings all the history the seine  the patiserries on every street corner the walkability of this beautiful city  did the 3 day open tour hop on  hop off bus tour to get my bearings then walked everywhere the next 3 days ate lunch &#47; dinner pretty much every day in the latin quarter ( rue st michelle across the seine from notre dame) - fixed price menu too good a deal   found the city extremely expensive tho
81  1461331840
it&#39;s was very easy to get around the ieffel tower and notre dame where beautiful
16  1461336888
it definitely could be the  world&#39;s capital city living up to its reputation and to all expectations
72  1461338730
lots of history and amazing places to see plenty of cafesunfortunately not many menus in english tourist buses great and easy to usetaking in all popular venues
85  1461338800
i highly recommend using the metro to get about in paris  you can buy 10 tickets for Ã¢â€šÂ¬14 in the stations or purchase a card to top up (like an oyster card) i bought the ten tickets and managed to see the majority of the attractions i wanted to see     to avoid looking like a tourist&#47;attention from pickpockets download the paris metro app which shows the metro lines you&#39;ll need to be on and where you need to change trains
4   1461338929
favorite places: ile de la cite with the notre dame cathedral and surroundings eiffel tower basilica sacre coeur touleries gardens and le marais neighborhood
38  1461342370
the touristic part was brilliant on a good day
20  1461348710
a great city for wandering plenty to stumble across without having to &#39;see the sights&#39; i felt safe at all times enormous range of great food and drink everywhere a true city of culture everyone was very friendly
56  1461350022
what isn&#39;t there to like about paris its all fabulous!!
10  1461351191
amazing buildings l&#39;open bus and boat package perfect for a general impression
25  1461351891
the friendliness of the people walking around discovering places for ourselves the many and varied places to eat  loved the designer shops but a bit disappointed that there were not more modestly priced shops also that so many cafes and resturants were closed on sundays especially as so many hotels do not do dinners
81  1461354274
i hate everything about paris
17  1461355464
paris met all our expectations but consider a minimum stay of one week necessary - it took a day or so to be efficient users of the metro
29  1461358511
paris is always special although compared to 18 months ago somehow poorer  the metro badly needs updating stairs everywhere and few escalators  lots to see and do for all tastes  the middle ages museum is particularly interesting managed an evening at the ballet which was fabulous good for shopping of course offering  something for all pockets  found some great little restaurants near the hotel although we had to search for them
22  1461369079
really expensive but it was worth!people were nice to us
10  1461387450
montmarte   eiffel   metro is easy
8   1461389556
efial tower    in different coffee shop we eat   it was easy trip and walking around the city   walking around and resting at different places
89  1461398631
lots to see easy to get around
67  1461398785
we were only in paris as a stop off en route to italy and to meet up with some friends who live there but paris is a wonderful city
67  1461402535
on our first visit i didn&#39;the like paris at all  maybe because some difficulties   however we keep returning and paris grow on you  we visit it on every europe trip    the food are good  many restaurants  shopping  art  sightseeing  museums  etc    it&#39;&#39;s easy to drive in paris and there are disney land for the children
60  1461402603
paris is one of my favorite cities it&#39;s great for just walking around because all the neighborhoods have a different flavor
25  1461406451
absolutely beautiful city although 4 nights felt enough i could have stayed maybe one more night but it doesn&#39;t matter as we will be back everybody is so friendly everything is so well keptclean and tidy if in doubt go! :d
97  1461407584
saint germain is a fantastic spot to base yourself and explore by foot central paris
78  1461409388
paris has just never disappointed me it&#39;s the most beautyful city in the world :-)    still i think the metro is difficult to find out
46  1461409497
i&#39;m little disappointed with paris last time i have been there didn&#39;t have so many beggar in the street everywhere you&#39;re going you don&#39;t have peace take care your handbag if you&#39;re going to paris!!!
87  1461411484
very easy to get around using the open tour bus you can get off at the different sites and a ticket includes the batobus a leisurely river tour again you can get on or off wherever you want also the metro is fast if you want to go quickly from a to b museums and the fact you can buy fresh food anywhere means you can have an impromptu picnic
26  1461411504
one of the easiest metro systems to navigate
94  1461412983
leave early to get sites in like versailles and musuemsbateaux mouche
17  1461413982
wonderful city great food and easy city to visit with a metro pass  take the time to hop on and hop off the metro and walk in different neighborhoods many places to to discover away from the tourist areas
82  1461415541
policie by mÃ„â€ºla hlÃƒÂ­dat aby se tam nepotulovali tacÃƒÂ­ kteÃ…â„¢ÃƒÂ­ makat nechtÃ„â€ºjÃƒÂ­ a na ulicÃƒÂ­ch vyspÃƒÂ¡vajÃƒÂ­ jinak krÃƒÂ¡snÃƒÂ¡ paÃ…â„¢ÃƒÂ­Ã…Â¾ jednoho dne zajde na ÃƒÂºbytÃ„â€º uÃ…Â¾ za francouzskÃƒÂ© revoluce nastala vlÃƒÂ¡da lÃ…Â¯zy tak by se z toho mÃ„â€ºli pouÃ„Âit
23  1461416346
paris is beautiful easy access for metrosbusrer but it depends where you stay
90  1461416434
paris is amazing everyone is so friendly and there is something for everyone from shopping culture amazing bars and clubs to cruise rides along the river and disneyland!    le marais is a brilliant district to stay in with lots of places to eat and drink and notre dam a short walk away!
64  1461416610
paris is always excellent and i have been to paris for many times
67  1461417232
visiting baha&#39;i holy place
17  1461418675
we took air france bus from orly ouest and then a taxi to hotel we caught the train back to the airport which was mich quicker we walked everywhere in paris and on the third day we enjoyed the hop on hop off bus the metro was useful too
19  1461418761
city much quieter than previous visits but life still going on despite lack of tourists  still plenty to do and seeexcellent food and wineparisiennes seem much friendlier and happy to see people coming back to their city
89  1461419162
very easy to get around on metro and rer systems be aware of pickpockets acting as official help in train stations and tourist attractions
10  1461420379
the most beautiful city in the world  lovely french lifestyle food drink cafes museums well dressed people polite bread la seine the eiffel tower
12  1461426615
enjoyed exploring to city - jardin des plantes seine boat tripm musÃƒÂ©e de cluny
34  1461430595
there were alot of beggers and homeless people which i did not expect- would encourage solo women travelling to not go out alone at night and not to talk to the beggers or make eye contact just walk past as some can be intimidating i would recommend never to leave your passport or money in your room whilst your not there i met a fellow traveller who had done this and had all her money stolen - be streetwise and not so trusting !! i went on the hop on hop off tour bus- make sure you choose the bus with wifi as some dont have it and you cannot meet your new traveller friends at later stops if your not on a bus without wifi thats what i learnt ! eiffel tower was incredible would definitly recommend !!! the weather was sunny and beautiful and notre dame was amazing- cannot believe this was a free entry! i would definitly go back to paris !!!
26  1461431431
arrogant parisians and very over priced
19  1461432100
open bus tour was great as saw a  lot in short time
49  1461434611
you must see the eiffel tower at night and during the day the hop on hop off bus is not a necessity as the loop is too long the blanket salesman ruin the eiffel tower especially at night with their alcohol and light sales the gypsy&#39;s at the eiffel tower asking tourists if they speak english and would like to sign a petition which is an obvious pick pocketing scam also ruins the eiffel tower notre dame free and beautiful not as many shopping areas as i would have expected
85  1461438286
it is not safe  it should be more  safer
89  1461439039
the view from the eiffel tower was amazing norte dame was beautiful and  old paris view was breath taking omg !! the louvre museum was one of the best parts to the trip  i&#39;m in love with the city  enjoyed the nutella crepes  metro service was great
60  1461439307
i originally booked 5 nights in paris and ended up extending for 3 more there is so much to do and see especially if you are an art lover    i got a 4 day pass to the museums ( highly recommend it as it lets you skip the line and covers many of the sought after destations)    paris is a place that i found one must really experience by slowing down the pace sipping an espresso and people watching is a big part of the culture    don;t miss the parks -- they were absolutely my favorite part about paris relax on a chair near a fountain reading a book and see life happen    i learned that in paris there is never enough time to see everything because i got the feeling that you can live a lifetime here and still discover new things everyday    my favorite museums: orangerie and orsay for impressionist works the orangerie has an amazing water lily collection    rodin museum is super for very animated sculptures see the gates of hell    the lourve i went twice once on wednesday night and other on friday night i spent 5 hours in total and both times felt like the museum was practically all mine     i adored the center pompidou for their amazing modern collections and abstract art the building itself was a lot of fun    don&#39;t miss the notre dame and also the saint chapelle --- the most stunning stained glass i have ever seen
17  1461454591
paris has two sides to it the pretty on show beautiful parts that are grand and clean and beautiful to the eye like the eiffel tower arc de triomphe sacre coeur place de la concorde l&#39;opera old classy architecture beautiful gardens like les jardins de luxembourg but the ugly side is where the poor live homelessly and lots of graffiti plastered in some areas and the dirty streets where cigarette butts lie and the mess some people leave in the streets the government should do something about cleaning it up but they don&#39;t there are definitely two sides to paris and to enjoy it you need to turn a blind eye to the ugly side and just enjoy the pretty side and the food and the culture
86  1461467856
paris has an ambience like nothing else i have experienced  such a special city !walking the boulevards and avenues was magical  i am not keen on big crowds so the main attractions are not my favourite thing but there are so many interesting things to see down almost every alley and laneway (if you are prepared to do some extra walking )
12  1461478455
i loved wandering around le marais district
19  1461482715
we were disappointed we couldn&#39;t find anyone to help us how to buy metro tickets or where to get it at the station so we decided to call a cab the cab driver who we thought would take us to our hotel in terrible traffic meter ticking the whole way and  we ended up about four blocks from station for 12 euros so we paid him and jumped out and walked across paris to our hotel (2 hours) dragging our luggage paris was wonderful we loved every second (after we dropped off our luggage) we walked and figured out the metro and took the bus up the river and the tour bus and went into as many special places as possible in our short 48 hour stay  i&#39;d go back in a heartbeat and would recommend it to everybody!  hit some great bistros our special treat angelina&#39;s for hot chocolate to die for expensive but worth every thick chocolatey drip  easy to get around once you can figure out took us one day  louvre notre dame catacombs rodin&#39;s house and gardens holocaust museum were some of the highlights of our trip weather was marvellous
20  1461483375
simply so much to see and the public transport gives you easy access to everything
78  1461483423
great city but not safe wallet got stolen not a good feeling when walking around the streets and not feeling safe wallet got stolen on the terrace of a restaurant!
56  1461484823
book ahead online for everything
75  1461486209
eiffel tower notre dame museums sightseeing     restaurant     metro was very easy
26  1461488280
every one was happy &amp; friendly loved the atmosphere at mont matre  &amp; along the seine
52  1461489944
an amazing city with lots to see and do make sure you bring good walking shoes!
96  1461490755
so much to see and the shopping is amazing
3   1461491965
paris is a beautiful city
57  1461492298
effiel towerdisney champ-elysee cafÃƒÂ©s
99  1461492383
louvre
76  1461493736
nice shopping &amp; site seen  what i didnt like is too much construction going on
29  1461494149
took an open bus tour which was perfect to hop on and off at our pleasure seen all the wonderful sites only wished we stayed longer!!
17  1461495531
the psg shop
43  1461497695
it was too short for me as i didn&#39;t had time
86  1461498107
fabulous
32  1461498418
if i could marry paris i would :)     i go to paris once or twice a month just for the weekend i can&#39;t get enough of this place  every place you walk there is something to see somewhere to eat a story to tell a photo to take
66  1461499494
paris is beautiful and breathtaking it gets even better when the sun sets and lighting takes its full effect you can sit anywhere on one of the many bridges on seine river and admire the most visited city in the whole wide world awesome !!
73  1461499543
this was our third visit to paris during this trip we were only visiting to take pictures with our granddaughters flat stanley; a school project we were able to visit the louvre without standing line and the crowds inside were very small
94  1461503348
just go!
76  1461506146
the french people were very approachable and friendly if u found urself a little lost we got the green hop on hop off bus to get our bearings then we explored alone bars&#47;cafes everywhere and all around same price for drinks and food queues foe louvre quite long get there early doors i think if eating out in the evening do it early the restaurant we hoped to eat in a thai one few doors from hotel wouldn&#39;t let us in as it was 1030 and they were closing up bit further down from hotel small bar on the corner family run very friendly and cosy
23  1461506577
paris the one and only !!!
25  1461507007
le tresor oyster bar has food to die for and a great atmosphere
23  1461507917
the choice of the restaurants shops  very noisy to me (i forgot how it was)
75  1461508253
paris is a must see for anyone interested in historical buildings and larger than life attractions  the people we spoke to where very helpful and one lady took us to the metro and bought her own ticket just to show us where to go on the metro    the marais is centrally located with shops of all types within walking distance  the weather was agreeable we saw many of the suggested attractions and will never forget the paris opera house which is opulent beyond words    we took a boat cruise one night and learned much about the bridges along the seine  the tour guide was most helpful in pointing out historical information
71  1461510552
paris must be walked to fully appreciate the city but temper this with the use of the metro which cost wise puts london to shame
92  1461511281
paris is a lovely place however the environment needs to be clean     the people are lovely and very friendly
89  1461511318
there is so much to do and see  everything is expensive  find out about the metro and bus services if you want to get around without miles and miles of walking
25  1461512058
the view from arc du triomphe was stupendous - worth the long climb  i loved the gardens in spring especially bagatelle  evening boat trip with dinner most enjoyable  best way to get around patis is take hop on hop off bus metro has too many stairs and long walks underground when changing line  musee d&#39;orsay is my favourite art gallery the new layout is a big improvement and the building alone is worth the visit!
26  1461512223
paris is the best place in europe fot walking shopping romancing just unbeatable
38  1461512383
overhyped destination
18  1461513923
always surprising civilised and charming
43  1461514134
fantastic art galleries
54  1461514576
the parisians often get a bad name for being unfriendly but everyone we came across was friendly and helpful paris has so much to offer for a city break
61  1461514792
i appreciated the change of enjoying classical and comtemporary art so close to each other   it&#39;s a good city to try food from differents countries for nice prices    i feel it has too much cars!!!
43  1461514925
paris is always worth a trip whether sitting in the jardin du luxembourg shopping or visiting one of the many museums it is always a great time
2   1461515359
had a great time in disneyland with my family it&#39;s also a leisure just wondering around; lovely cherry blossom behind the notre dame
72  1461516059
musee d&#39;orsay - great louvre - great palace of versailles - excellent  beer and wine - greatly overpriced in cafes bars restaurants even moreso when they are suposed to be the best winemakers !!!  getting around by metro&#47;rer simple and good  what&#39;s happened to the baguette - very hard and crusty - almost doubles up as a cudgel  under 4euros to versailles by rer charles de gaulle airport which is equal-distance is 10 euro
93  1461517809
too expensive of a city but very beautiful to walk around and explore!
75  1461518262
avoid the dog shit on the pavements
47  1461518283
fantastic city with plenty of sights to visit we
21  1461518436
perfect
98  1461520419
paris pass including metro tickets fantastic seine cruise with dinner  muse d&#39;orsay superb meal at chez toinette one of the best restaurants in paris
22  1461520639
paris is an awesome city unlike any other - architecture culture art food wine  the list is endless
70  1461521616
the highlight was the lunch river cruise of the seine with bateaux parisiens  great food and great service moulin rouge show and entertainment was worthwhile but very crowded with an ordinary meal
73  1461523189
i was hopping to see paris before because i have seen eiffel tower and the champs elysÃƒÂ©e on the tv but that is the first time and the last time for me to go to paris because every thing is expensive and specifically the hotels and everybody want to take your money and the were not a good smell in the streets it seems like a dirty bathroom and when you walk in the street you see chaos of cars and motorcycles the  good thing in paris is just to take a picture with eiffel tower and champs elysÃƒÂ©e
75  1461523780
city of love &lt;3
34  1461525643
be careful with the thief who can take your money pocket in the metro lines
59  1461530314
we loved the museums the architecture and most of the food we used the metro to get everywhere an evening cruise on the seine was a real highlight as was the musÃƒÂ©e d&#39;orsay
51  1461531919
paris is a beautiful city with lots to see and explore around it is important to know a bit of french to start communication with french people around it is very easy to get around paris if you are a couple for a family with small children it is difficult as not all metro stations have access to elevators great architectures and beautiful sites to explore
17  1461532632
beautiful city full of life plenty to do and see great for restaurants and cafes stay on the left bank if you want typical french life
47  1461533079
if you want sights paris is the place to go! so many wonderful attractions ranging from the eiffel tower to notre dame we walked and walked all of paris - you see so much more! such a great feel to the city plenty of little nooks and crannies to explore and loads of restaurants prices were okay a little on the high side depending on where you were arondissmont wise sadly the only thing lacking were an abundance of fresh vegetable sides on most menus!
18  1461533187
ok
57  1461538642
at some places especially - metro station most of staff don&#39;t speak english big disadvantage for non french speaking tourists otherwise is easy to get around by metro when you follow sings lots of people trying to sell souvenirs at street most of the time a bit too pricy and almost uncomfortably demanding your attention even if you not interested forcing you into buying staff so be carful
19  1461541108
the metro is brilliant in paris i&#39;d advise staying somewhere with free wifi so that you can plan journeys and pre plan metro trips we stayed for 6 days and it was just the right amount of time to do everything on one of the days we used one of the tour buses although a bit tourist-y it was great for getting to all the main attractions because you could hop on and off as much as you pleased we did most of paris in two days and you did learn a lot about paris   i would defiantly recommend visiting the eiffel tower in the evening just before the sun sets it is a lot quieter at this time so there was no queue and we saw a beautiful sunset   if you are self catering be prepared as it is expensive living although no different to london prices   lastly if you are visiting the louvre you will need a whole day even of you are not an arty person it is still an amazing day out!
8   1461544723
so much too see you need 5-7 days to see all the museums churches and louvre bike tour was a  great way too get a taste of paris
11  1461545357
loved our location easy to get around on the metro  paris very vibrant and happening  loved the free walking tours  montmartre latin quarter as well as all the churches  be careful when choosing a restaurant don&#39;t go for something you google in a tourist area  better to wander around go for something small found in a back street
16  1459241336
metro good value
64  1459244257
staying in st germain area was central to walking to the many sites of paris
37  1459244703
paris is beautiful main attractions are eiffel tower the cruise around the tower and disneyland the public transport routes are very easy to understand and the staff at information&#47;service centers speak good english to help foreigners reach their destinations i wasn&#39;t very interested in museums so i avoided visiting those    i must mention that people are very helpful and go to any extent to help foreigners myself with my 4 year old son and wife were travelling back from disneyland to bercy it was around 11:00pm or so at night when we got down at nation railway station to catch m6 route metro to bercy unfortunately on that day m6 route metro got cancelled on that station due to track maintenance being new to paris we didn&#39;t know how to make our way back to the hotel while we were desperately searching for help or an information center in the station one guy (who was obviously going somewhere else and appeared to be in hurry) stopped by and suggested us to catch m6 route metro from daumesnil station not only that he also walked us all the way to the nearby bus terminal and helped us with boarding a bus to daumesnil metro station he explained the situation to the bus driver and left in a hurry we couldn&#39;t even thank him properly however we reached daumesnil station and took m6 route metro to reach bercy from where our hotel was just a couple of minutes away     it makes me happy that such helpful people exist the only way i can possibly return the favor is by helping someone else who is in trouble
49  1459245073
architecture and sights are magnificent but the number of tourists and tourist scammers (shady individuals selling all kinds of things or trying to trick you to buy) is just awful can barely enjoy anything
56  1459245663
no street parking available and parking houses are very small for large cars  otherwise everything was great
49  1459246493
try to walk sa much as possible  paris is beautifull on foot
37  1459248328
paris is one of my favourite cities in europe and i have visited over 200  it has something for everyone who dosen&#39;t want a beach holiday
51  1459249300
amazing city either during the night and the day!
73  1459251121
the metro didn&#39;t have much english on their maps so i would recommend knowing the names of the areas you want to go to in french
25  1459251332
i usually ate at little kiosks they have very nice menus!! sandwich+drink+dessert for less than 10 eur    go for the paris pass of transportation! it was the best decision i ever made! because i could use it the times i wanted within the days i chose
32  1459251845
romantic city specially in winter
5   1459252231
very very nice place one should be accompanied with his&#47;her partner
57  1459252296
eat lunch at hotel costes l&#39;avenue and las du falafel eat dinner at yeeels miss kÃƒÂ´ georges and la favorite take a photo of the eifeltower from trocadero and party at l&#39;arc and le baron spend your sunday brunching at st regis shop at rue st honore and on avenue george v have an ice cream at amorino if it&#39;s sunny
68  1459252710
great city
47  1459257016
paris is beautiful although it&#39;s not what you really expect typical city a must to book eiffel tower in advance as it&#39;ll take time to queue and you don&#39;t want to waste your time queueing river cruise is good as well
46  1459257419
catacombs were amazing
60  1459257563
love it
98  1459257666
we went for a gig paris is generally expensive (and we live in london!)   public transport was really easy as i used google maps and an app for the metro so it was easy peasy     bought food to prepare ourselves
49  1459257700
disneyland  tour bus
30  1459257844
la coupol excellent as always
59  1459257913
lovely clean area friendly people
59  1459258075
louvre was very nice but i was concentrated just on few things so quick visit there sacre coeur lovely view eiffel tower is amazingly high!cafes by notre dame worth to spend money at moulin rouge is overrated dirty and lots of homeless and drunk people
6   1459258270
buy your tickets online from your phone while outside the venue then pick up tickets from newspaper vendor to skip the queues!
96  1459258332
the roads and directions are awful
52  1459258466
visited some wonderful places would recommend versailles palace montparnasse tower (fantastic views) sacre couer louvre wonderful little zoo in the gardens by the river the metro system is extremely easy to use and a great cheap way to get around
6   1459258572
i love paris but it&#39;s so much effort with kids    definitely somewhere for adults only
16  1459258835
restaurants and bars near to hotel people very friendly and helpful
54  1459259058
amazing city! one of the most beautiful places i&#39;ve ever been when you walk around the city you feel amazed everywhere you go incredible views and architecture i&#39;ll definitely come back
4   1459259444
everything about paris was awesome   food was excellent   the hotel location was excellent and we could go around everywhere by foot  the river tour was amazing and a must for anyone visiting paris not to forget to go up the eiffel tower
43  1459259800
it doesn&#39;t feel safe out there especially during evening&#47;night
16  1459260925
definitely do the bus tour as this gets you around the city easily  if you want to go up the eiffel tower book the restaurant in advance on weekends to avoid horrific queues  eat anywhere except the main tourist areas (st germain and the square past the grand palais &#47; petit palais is lovely - and definitely visit laduree for afternoon tea (either champs ÃƒÂ©lysÃƒÂ©es or rue royale)
0   1459261410
impressive city with a lot of culture buildings bars and restaurants  excellent metronetwork
69  1459261495
eiffel tower is clearly the highlight transportation is very convenient here mist hotel do not offer breakfast or tea&#47;coffee facilities in the room which is a downer but paris is still amazing and a must do!
95  1459261686
i saw everything i wanted to see so i was very happy with the visit
81  1459261783
you can easily walk around the city for those who want a more leisurely pace go for a cruise try the batobus you can hop on and off be aware the main tourist attractions are always busy
45  1459262028
our wallet was stolen by someone in front on eiffel tower
79  1459262128
public transport was easy and convenient loved the stained glass at st chapelle great vibe on the champs elysee amazing architecture everywhere
81  1459262208
love paris !!!  love marais  love the great shopping  love escargot !  you just have to deal with the crazy prices !!!
69  1459262670
saravana bhavan
1   1459262814
had a wonderful time seeing all the key sites of paris: eiffel tower arc de triomphe champs-elysee tuilerie gardens place de la concorde the louvre notre dame sacre couer montmartre versailles queues for tower and for chateau de versailles are massive expect a minimum of two hours to queue even in early spring in summer it would be horrendous however if you book for the tower in advance you&#39;ll get on much more quickly thankfully we had    despite its reputation for gastronomy we didn&#39;t find any particularly noteworthy restaurants they are mostly expensive (comparison made with 120 euros to 1 gbp) and food pretty average not bad but not great either    car parking is extremely expensive we parked in a car park well away from the centre and it cost nearly Ã¢â€šÂ¬50 for 36 hours    overall though paris is a beautiful city and well worth a visit
23  1459263371
- i would suggest visitors to spend a whole day in paris streets and see the passage of day rain and sun and   - army soldiers were all around the attractions because of the terrorist attacks it was both feeling safe and scary!
43  1459264570
louvre and boat trip on seine
11  1459264920
we tried to experience the real paris - we went to chalet des iles - restaurant in bois de bologne area - novel experience with excellent food and service the moulin rouge show is a must see - we had the dinner and show experience and although quite pricey was well worth it phenomenal acts and lovely food &amp; service the train journey from the airport to paris was easy - trains and trams are very efficient and the best way to get around the open top bus tour was a good way to have an overview of the area the sacre coeur was  beautiful but overrun with street artists wanting to draw youa little irritating and beware of pickpockets around here and other scammers doing card tricks etc in the streets  the french were extremely pleasant and helpful - we got offers to help out on the trams and around the city  our hotel villa d&#39;estrees was situated in the latin quarter which we found ideal as it was very close to the seine notre dame and the metro it was a very peaceful quaint hotel with chic traditional decor - the staff couldn&#39;t do enough to help us we would definitely go back
62  1459265287
we did not anticipate the cue for the catacombs it opens from 8am so get down there first thing in the morning as it is up to a 4 hour wait! take the headset for the full tour experience     for nightlife visit the australian bar near the catacombs open from 11 Ã¢â€šÂ¬15 in but this gets you a free cocktail with the coupon music to suit everyone very busy friendly atmosphere!
97  1459266618
food and drink was kind of expensive compared to back home
31  1459268200
what&#39;s not to like about parisit is a wonderful city steeped in history we found paris easy to get around and managed to see so much on footwe caught the metro twice in 6 days if you love walking  then walk the streets of paris you will discover something enchanting at every turn
69  1459268943
great city  plenty to do  great trip
75  1459268978
love parisa must
51  1459269004
i liked the gardens a lot!     we walked everywhere and the last day tried the velib bikes which firstly you should read about their use because they are a bit challenging if you are not informed     paris seems very big but actually you dont need to stay necessary in arrondissment 1 to be near to everything we stayed in arr6 and it was very central!    book tour eiffel and other attractions ahead online
75  1459269276
place de vouge
17  1459270795
it the good city for walking if you are tired bityou can find the cafe anywhere  amazing food is waiting for you
8   1459271114
totalmente cautivador
22  1459272391
everything best city
92  1459272754
transportation is very expensive
95  1459273072
i love the atmosphere in spring walking by the river looking at the bookseller&#39;s stalls amazing places to visit - try st chapelle fabulous windows and musee de cluny to see the amazing tapestries of the lady and the unicorn and don&#39;t miss the musee d&#39;orsay for the impressionists
68  1459273564
great trip highlights would be montparnasse tower seine boat trip montmartre at night and of course the notre dame cathedral
93  1459274310
there is so much to see and do in paris i wish i had a little bit of more time to stay
52  1459275544
wonderful city with lots to do  we walked to trocadero to take pictures of eiffel tower at nightlit up travelled by rickshaw to louvre for 30 euro and got a great tour guide into the bargain audio tour was incorrect and took us out of the louvre so we had to go back through security guards let us jump queue which was kind you are allowed to take pics in the gallery    we were searched and scanned  frequently but nobody minded this
56  1459275772
queues for attractions exhibitions galleries etc  are extremely long so expect to wait in line for at least 45mins- 1hour best to get your tickets on-line and in advance if possible although this won&#39;t guarantee that you&#39;ll have a shorter waiting time    if you&#39;re catching the eurostar from london you can buy a carnet of 10 metro tickets at the main info desk in the departure hall it costs Ã‚Â£15 card payments only    if you want something uniqueand healthy!to do in paris take a walk or a jog along the coulee verte renee-dumont which crosses the length of the 12th arrondissement more info and photos here: http:&#47;&#47;wwwfrancedigitalecom&#47;randoweb&#47;en&#47;58
73  1459276011
pre-book where possible! we spent the morning queuing and enjoying the eiffel tower when i should have booked to skip the lines same for the louvre  buy a day ticket for the metro it;&#39;s so easy   must do sacre coeur and notre dame they&#39;re stunning arc de triomphe nothing really special
89  1459276039
wine cheese coffe landmark repeat
2   1459276101
not for those who don&#39;t like queuing or proximity to people who don&#39;t like queuing! otherwise it is a great cultural experience
57  1459276102
any students from eu countries can visit musee du louvre and arc de triomphe for free with visa in paris disneyland i was so disappointed because i entered walt disney studio by mistake i supposed to get into disneyland park which was a lot more fun compared to walt disney as i purchased only 1 park when i realised that i ran into disneyland park entrance and as expected they didn&#39;t allowed me in because i&#39;ve used the ticket to enter walt disney studio  as a muslim i can eat only halal food my daily breakfast was mcdonald egg and cheese burger with coffee which costs me only Ã¢â€šÂ¬250 my lunch and dinner was quatre kebab and istanbul delight  it is easy to get around paris i bought 3 day pass for unlimited journey zone 1-3 for Ã¢â€šÂ¬23
79  1459276591
easy to get around very expensive to eat
78  1459277005
loved it i will visit again!!
94  1459277602
the metro is very easy to use we got a 3 day travel card that cost us less the 25 euros  use the hop on hop off bus you see over 50  attractions you pay 20 euros for the whole day  the efffel tower is amazing but lines are very long if you go in peak season
26  1459278185
i highly recommend using uber if you cannot speak french grab someone nearby who can and they are usually nice enough to assist
98  1459278943
the best tip we can offer is to book your visits to the museums in advance there are always lengthy queues  and we mean lengthy!! if you book in advance you can go straight in which is a big bonus book your restaurants in advance so no wandering around wondering where to eat - le petit zinc le petit pontoise and the vagenende would be our choice! the left bank is really buzzy much better than the rather stiff and sterile right bank!! we stayed in le vieux paris hotel which was great right on the seine and the place st michel right in the middle of everything
16  1459280326
its nothing to say you must go and see this amazing place
64  1459280519
love paris
64  1459281111
paris is a wonderful place to visit but unfortunately i  did not have enough time to explore all it has to offer due to time constraints  i made the most of my 3 days in paris though
47  1459281475
there&#39;s so much to see and do and having to lost one full day in paris  i felt very cheated
45  1459281481
paris is great but really expensive??i live and work in london and can tell paris is more expensive in terms of super market&#47;food and drinking-going around
43  1459281537
definitely worth a visit will be going back to finish all the things we didn&#39;t get round to doing
32  1459282349
the best thing to visit are louvre and notre dame unfortunatelly paris is not so beautiful how some people says it&#39;s dirty and people unfriendly  it&#39;s easy get around paris by walk and by tube
21  1459282605
a beautiful city with plenty of sights and romantic locations good enough to propose in ;)
35  1459282962
great city went for the rugby - easiest match to get to on 6n circuit by far easy transport well marshalled by police
19  1459283364
very easy to get aroundbuy a day travelling pass at the metro and u can travel all day for cheap if u don&#39;t fancy walking definitely buy skip the line tickets for the likes of the eiffel tower and the louvre musuem otherwise you will spend the day queueing the latin quarter is lovely for dinner and drinksoverall it&#39;s a beautiful city with some amazing architecture
9   1459283905
a city which is a huge musuem !! is good for 5 days!
94  1459284974
it&#39;s very convenient location hotel is few steps away from supermarket and around corner is a filipino store we&#39;ve stayed in a 2 bedroom apartment suite with a small kitchen so we are able to make simple breakfast that saves our money of eating outside or eating at buffet inside the hotel which will cost you 13 euro per person
21  1459285125
paris is brilliant lots and lots to do!
75  1459285320
loved seeing the eiffel tower lit up at night did the seine river cruise at night and would recommend this to others were not able to go into the notre dame cathedral due to it being easter sunday very long lines and short on time visited the louvre museum highly recommended
90  1459286052
i went primarily to see the art so loved the big art galleries as well as the smaller ones around st germain-des-pres which in itself is a great area to wander around and get lost in i also enjoyed walking along the river and took my camera and sketch book i ate at various places including a crepe from a street van to a meal at les deux magotwhere i also enjoyed a marvellous glass of sancerre rouges  getting around was easy enough on the metro and an experience in itself with the talented buskers around and different styles of station the franklin droosevelt station was particularly impressive i thought i did try waiting for a bus once on arrival but after 10 minutes and not knowing the times or if i needed correct change got bored and decided to go for the metro instead   for me travelling alone i really wanted to reconnect with my creative side so having the freedom of going solo enabled me to observe absorb and reflect more on my surroundings and think without the distractions of other peopleif you just want to get away from it all i would definitely recommend solo travelling it&#39;s quite a meditative thing to do and basically i didn&#39;t want to do anything that i could&#39;ve done at home so instead of going to concertspubsetc i explored the city as fully as possible it was my first time abroad and my french is also poor so i was a bit anxious at first but that soon faded by the excitement of going on an adventure and i kept my phrase book handy and read up on advice for staying safe the french i encountered were friendly and helpful too it was definitely a liberating experience and what better place is there for that than in paris i don&#39;t if it was because i was on a break but the atmosphere to me seemed very laid back compared to english cities where everyone is in a rush to get somewhere i don&#39;t smoke but it&#39;s very smoker friendly if you do smoke i didn&#39;t see any drunk loutish behaviour which was nice also i had a great little boat trip on the seine and is a great way to see the sights with the weight off your feet if not into long walks i loved the shoe shops and my only regret is that i didn&#39;t take more spending money i&#39;m looking forward to going again
73  1459286766
excellent city great architecture history and culture not the most friendly people in the world though at times quite rude to be honest especially the staff at even the best of restaurants drivers in taxis and attendants and different shops  the louvre is great eiffel tower is a treat to behold and gives a majestic view of the city be mindful of the fact that the queues are always long so its better to book an e-ticket and reach the site really early  there are lots of hidden gems throughout the city discover them its fun
84  1459286791
plan way ahead and buy tickets online
95  1459287306
view from sacre coeur has to be seen and the overall spirit of the city is just nice  the eiffeltower is nicest at night illuminated
53  1459289399
people are not friendly and the underground is smellythe street is duty and it not safe to go out at night
25  1459290299
not knowing french is ok as most people can speak english
9   1459291714
my husband and i loved the sights and tastes of paris! we were worried about getting around and not expecting the french to be very helpful we couldn&#39;t have been more wrong everyone was very helpful and the metro very easy to use we were only there for a weekend but saw all of the sights we were interested in
28  1459295595
it seems that paris is getting its strength after the terrible events  i enjoyed  very much the salon of agriculture in the exhibition halls at porte versailles
16  1459298000
paris is paris  this is my 5th visit and sure i&#39;ll visit soon
31  1459300162
moulin rouge worth the visit for a show  recommend looking at the (old) opera  the eiffeltower from the tuilleries always a sight to behold  cite metro tickets a great way to get around freely  moving on to london with the eurostar from gare de nord no problems  much more to do and see - discover your own paris - but beware of pick pockets (metro mainly) and do not stop for groups of young ethnic men hanging around wanting to &#39;show you a trick&#39; ie at the bottom of sacre coeur
16  1459311238
the biggest city in europe! full of modern and beautiful places lots of shopping area museums located everywhere easy for travelling with bus tours trains and cruises great for visitting!
98  1459313116
stay in centrum
44  1459314583
i have visited paris a few times and my biggest advice is to give yourself time to appreciate this lovely city; don&#39;t book your days with sightseeing walk stop at cafes sit in the tuileries with a good book and cafe wander
98  1459319760
people do not speak english and they keep replying to french very annoying
82  1459321135
there were many nice places to visit like eiffel tower defence gate and shopping there were amazing its better to rent a car there if you want to travel long distances because it will be cheaper than taking taxis especially if you want to go to the outlet and disneyland and other far places
1   1459322558
eiffel tower  local restaurants   simple with the metro  the lovre big bus sight seeing tour a must!
91  1459323203
book tickets online before arrival or visit a paris tourism kiosk and book tickets get to places as early as you can long ones for security see a ballet or opera walk!
61  1459323318
i always like paris i have good business relations good friends enjoy eating food nice shopping areas i always like coming to paris
42  1459324568
people were polite and friendly getting around was easy the sights were incredible lovely cafes and small restaurants
18  1459327454
go eat yourself fat paris is all about the food try the brasseries bistros boulangeries and markets stroll around the smaller streets to find cool shops graffitis or architecture plan about 2 hours of queuing for the main sights and try to book the tickets online ahead of your trip (eiffel tower louvre)
3   1459330560
great place for food shopping and culture
2   1459332074
the unpredictable weather at end february 2016 !
69  1459332098
a great and beautiful place !
26  1459332463
eiffel tower is good louvre is amazing but too many thieves i saw at least 8 people trying to steal others pockets public security can be improved
19  1459337149
paris is amazing
5   1459343209
le ombres restaurant fantastic loved the eiffel tower didn&#39;t make much use of metro which would have been easier to travel around but most attractions within 20 mins walk!!
8   1459345647
beautiful buildings  everywhere and loads of cafes  eiffel tower arc de triomphe grand arch (in the business district) pantheon statue of liberty replica notre dame montmartre all really impressive and entry prices reasonable  orsay museum better than louvre if like us you don&#39;t know much about art  metro was really easy and pleasant to use as was the river boats  kids enjoyed the eiffel tower and roue de paris ferris wheel the most but neither good if you have vertigo!!  we mostly went to rue de la harpe area for reasonable fix price menu meals
79  1459345988
good points to see a lot of story and culture
89  1459346031
we went friday  - monday which wasn&#39;t long enough will have to go back at some point to see the sights that we missed   highly recommend going to the moulin rouge great show   there are lots of metro points all around not that we used them we walked everything had a good tour of the city   if you go to the louve buy tickets and use the side entrance(carousel) much quicker to get into and allow a good 5&#47;6 hours to walk around it
11  1459350363
paris is perfect for couples in love if you value historical places and love slow paced european life then this should be your first choice
84  1459350492
there is no securety check in the metro stataions !!!
40  1459354380
we visited the eiffel towerthe louvre notre dame &amp; the arc de triumph all of which were easy to find   we also enjoyed a riverboat dinner cruise on bateaux parisien&#39;s which is highly recommended the views from the boat of paris at night were amazing  the choice of cafe&#39;s bars and restaurants is endless and we found the prices to be fair  we would definitely return to paris in the future it&#39;s a beautiful city with plenty to see and do
10  1459364340
try to avoid doing main attractions at weekend as it is very busy and queues are long
72  1459366434
best place to visit this was my third trip to paris
56  1459366496
you can buy gourmet food in any foodstore and enjoy the charm of the city
45  1459369730
beautiful city amazing history but too many immigrants it&#39;s scary to walk streets in the evening government must to do something with it
93  1459371963
what i like about paris:   - you have lots of places to visit   - nice restaurants and lots of cuisine options  - metro connectivity is simply excellent  - beautiful ladies     what i dislike about paris:   - any sight-seeing place you visit - it is always crowded and there is a never ending queue you need to book in advance to get a fast entry  - safety - you will never feel safe in metros or in the metro stations or even on the street for that matter - pick pocketers snatchers are lurking around you all the time - did not travel much by trams or buses - so don&#39;t know about safety in those 2 modes of transport  - too many immigrants - mostly illegal ones paris must seriously do something about this  - not a very clean city like other major european cities
9   1459375420
paris is a wonderful city which has a lot to offer in terms of culture food and fun   if i had to name just two reasons to visit paris it would be sights and desserts!
46  1459380911
1) the city was fantastic and excellent    2) lot of historic places to visit and heritage areas    3) lovely gardens    4) but unfortunately i saw few people were urinating outside of prestigious building and on tree trunk    these peoples are making paris dirty
2   1459390682
everytng is great must see
57  1459395619
paris is so wonderful and adorable city!   its like a dream come true so much to see and to learn! definetely will be back!
74  1459397062
expensive but should be on everyone&#39;s bucket list for at least one visit
47  1459410874
our favorite places in paris were notre dame tour eiffel and louvre museum
57  1459416032
very expensive
43  1459416501
the city is even more beautiful at night the food (cheese!) and wine were fantastic
94  1459416773
great place to be  we had some amazing meals in the latin quarter as well as doing some good shopping in the st lazare area however i ended up being pick pocketed on my way to the airport and losing my mobile be careful!there is nobody to report thefts to in the airport so you will not be able to get docs for insurance also - the weather was really unpredictable take a jumper and an umbrella as well as sunglasses
64  1459417055
dangerous city for tourist please be carefully about yr belonging and your life
24  1459418901
plenty of places to eat but all not that good    busy city but as bad as london
86  1459418938
everything is very expensive and the usual strikes can make travelling difficult
88  1464531740
enjoyed strolling along seine and through luxemborg gardens
19  1464531985
paris is a beautiful city  5 days wasn&#39;t long enough
89  1464533718
first visit in 10 years lots of fondly remembered favourites changed or gone all in all paris is paris; as lovely as ever
7   1464533775
personaly i love paris but it can depend on allsorts of point of views !!
10  1464533800
galleries lafayette  champ elyse louvre  palace of versailles eifel tower
12  1464535321
food &amp; drink is quite expensive but lots of choice of restaurants &amp; bars  loved the ile de la citÃƒÂ© especially notre-damn  enjoyed just wandering around especially there and the latin quarter  really enjoyed the use of the batobus (hop on-hop off boat tour)  good transport options around city but with a centrally located hotel alot is accessible on foot
14  1464539800
loved the batobus great way to see the city musee d&#39;orsay is amazing for a rainy day french open at roland garros was the highlight
6   1464540326
getting around is cheap and easy food is amazing and places to visit are plentiful
43  1464540721
place de voseug
28  1464541574
it&#39;s paris
12  1464544178
paris is one of the great cities of the world there is so much history in paris and it is a very walkable city the tourist sites are very busy so it is important to book ahead if you want
40  1464544732
lovely city fourth time we have been there great architecture and lots of history reasonably priced food tea and coffee and soft drinks above average prices so if two of you want tea share it as its always served in a pot and have the small coffees that the locals drink as they are cheap if one is not enough have another - still works out cheaper open top tour bus is worth doing as you get a commentary of the history of paris and can get on and off where you want scare coeur and montmartre are probably our favourite places in this great city
68  1464544999
getting about by bus is easy with a carnet prefer to the metro as you can see the city loved the national archives museum fascinating documents lovely architectural features the gobelins tapestry museum and factory tour was good in a non touristy area not far from the hotel  seeing the tomb of cardinal richelieu in the sorbonne chapel fulfilled a long ambition as did visiting his favourite home at rueil malmaison which is also associated with the empress josephine we always love sitting in the luxembourg gardens a canal cruise on the canal st martin was a pleasant sitting down change to site seeing st sulpice church worth a visit and also enjoyed the nearby luxembourg museumnot really impressed with the food inparis rather expensive and most we had was mediocre for the price cannot wait to return however love paris
13  1464547087
the metro is a convenient means of travel everything is fun in paris rue moufftard has some great restaurants
60  1464547669
more expensive this year to eat out but metro still such good value and efficient easy to get around and never too far from a restaurant bar supermarket and shops and bus stop  versailles amazing but so busy
82  1464548385
prepare to walk there is so much to see!
77  1464548566
it&#39;s always a pleasure to spend some time in this most comfortable and elegant of cities
88  1464549338
eiffel tower louvre museum arch de triomphe champs elysee
24  1464549626
went to watch the french open tennis getting to and from the stadium very easy by the metro weather was not the greatest- cold and wet
4   1464549739
fab city friendly people
45  1464549990
i loved the notre dame and i thought the metro system  greatpeople could have been friendlier
81  1464550557
we used the venue to visit the palace of versailles one day and also the gardens of giverney and vernon the previous day    we ate at restaurant 41 on avenue mozart and would recommend the restaurant
92  1464550777
paris is a beautiful city to visit  the only problem is being plagued by touts selling rubbish souvenirs at every decent tourist attraction  don&#39;t allow yourselves to be intimidated by them
0   1464550911
a little bistro just around the corner from the hotel
84  1464551206
nice vibrant city
0   1464551225
lots of things to see and the dinner cruise was excellent
98  1464552353
i love french pastries there were so much to see  at louvre museum  enjoyed the boat trip and eiffel tower notre dame was amazing architecture    we got lucky a local french gentleman chased away pickpockets from stealing things from us     be aware of clever pickpockets they would approach with clipboards pretending to be deaf and dumb and ask to sign up some sort of petition to keep you busy while waiting for opportunities to pickpocket
53  1464552369
paris a beautiful place to go but very expensive paying Ã¢â€šÂ¬6 for a cup of tea and Ã¢â€šÂ¬10+ for a small bottle of beer is unreasonable but sadly all too common in central paris soft drinks often cost as much as alcohol so not the best place to go out but perfect for long walks very picturesque the metro is quite easy to use make sure to buy bundles of 10 tickets they are cheaper and work across paris including buses restaurants need to be researched as we found a lot of mediocre food for insane prices however there are also many good restaurants too you just need to know where to go
91  1464552805
not my first time in paris level of enjoyment depends on the area in which you find yourself eg area around sacre couer is not the best whereas big tourist attractions like eiffel tower were fine only irritation was the amount of people wanting something for nothing especially coming from somewhere like africa to have to experience this in a first world country where our money is not worth much against the euro i will always love paris but for a south african it is not affordable and not worth the price you have to pay these days unless you want to skip the restaurants and stay in less desirables areas and&#47;or hotels
30  1464553418
rodin museum and monets at l&#39;orangerie
3   1464553976
just loved everything did not have a bad experience walked everywhere and the metro is awesome    we loved pizza pino&#39;s restaurant too!
16  1464554925
effiel tower was amazing 15Ã¢â€šÂ¬ per person to get on with the lift (recommend as the stairs are way too long) we had access to the park in front of the tower where at night people will gather and chill drink and watch the tower light up amazing experience went to disney land by metro easy to get there 45-1 hour journey restaurants were avarage price very nice food brilliant experience will defo go again!!
82  1464556577
a lot to see but dirtyespecially the metro
48  1464559116
so much culture to enjoy but the metro system was certainly not handicap-friendly very few elevators or escalators if you are able bodied then you surely can get around to most places because of a very comprehensive subway system
19  1464560357
metro was easy once you get the hang of it  bus lines and streets are confusing
71  1464561352
ease of getting around on transport system beautiful clean city
58  1464562715
that most of people in paris can speak english and ready to help out
24  1464565735
amazing city
19  1464575250
love every steps n corners in paris  the seine cruise was a cheap way to reach most major attractions in paris
98  1464584173
fantastic as always with the sightseeing  please be very guarded when you are in opera district as i nearly got robbed by a gang of young women in broad daylight and no one was helping  please really allow extra time at the airport for the tax refund - the process is very time consuming
37  1464585755
loved the culture  everyone we met was so hospitable and very forgiving to us for our very poor attempt at speaking french  there was so many icons to see that we literally ran out of time which was so disappointing  can&#39;t wait to get back to france and in particular paris
99  1464589073
paris is beautiful food is so much better than london but the french is simply unfriendly  you just have to enjoy yourself ignoring the rudeness around you    one day at louvre is too rush contrary to the french we love the glass pyramid  disneyland is just ok    half of champ elysees is closed down during 1st may    leisurely stroll down the street of paris enjoying the view is great  eiffel tower is a must go
45  1464589664
metro travel easy-roland garros wonderful but very crowded
1   1464589713
paris is amazing!easily the best city in the world!super easy to get around the metro is great and easy to get to and from the airport its good for families groups of friends couples and perfect for solo travellers
51  1464590750
fantastic city to visit beautiful architecture and sight to see  catacombs of paris a must to see
23  1464593088
it is still unbeatable destination in europe for both sightseeing and great variety of evening activities check for paris events before going to paris
48  1464594227
ahhhh paris it lived up to its reputation as with rome the rich history art and culture from the eiffel tower the louvre museum notre dame cathedral i could have been robert langdon and not realized it!!!
96  1464594697
for me paris is the most beautiful and magical place in the world  i have no more to say - simplement parfait!
42  1464596048
i have been to paris many times before and this time i was pleasantly surprised by all the new areas that i haven&#39;t visited such as rue royal and rue du faubourge saint honore for my luxury shopping    i also managed to visit some great restaurants while i was there my advice is if you&#39;re after good food do not follow the recommendations on trip advisor but to ask your hotel concierge we were recommended this restaurant l&#39;ardoise which was wonderful additionally follow instagram food bloggers like @juliezwing for the best new restaurants to go to in paris
32  1464596178
fashion fashion and fashion
6   1464596412
we enjoyed paris very much the people and sights were excellent the eiffel tower was very impressive in the daytime but was spectcular at night as were all the other sights we visitedthe metro system was easy to navigate disneyland was fantastic as were both hotels we used for our stay in paris would recomend the open top bus tour and the boat tour at night is fantastic
98  1464598697
plenty to see and plenty of places to eat some of us went to and had a good time at the french tennis open which seems good value for the money compared to wimbledon    metro is very convenient
63  1464599595
my favourite places are the flower market (now named after queen elizabeth ii) the tour st jacques  the rue mouffetard market and the banks of the seine with the &#39;bouquinistes&#39; favourite eateries: l&#39;atlas on rue de buci  (wonderful oysters and pate de fois - excellent service ) and polydor on rue m le prince (try the chop de boeuf ) getting around paris is so easy and fast on the metro - buses are great too as a woman of a certain age travelling solo i feel really at ease in lovely paris
72  1464600131
easy to get around the metro some lovely eating places near to stay  river trips a must but trip to top tower cudnt do as queue too long lovely wines at supermarket to sit at night and watch everyone go by
50  1464600778
paris is perfect for shopping and nightlive a lot to see and explore  recommended for everyone
4   1464602259
you can find whatever you are looking in paris city   easy access to metro and clearly written everywhere staff in metro are helpful the sncf staff   paris by night is crazy so much fun and clubs are awesome stayed in the hotel right in the city and lots easy to go around and safe
50  1464604590
a great place to wander round
76  1464605119
we are all ex soldiers and our visit to paris was specifically to see the musee de l&#39;armee &#47; les invalides - napoleon in saint helena exhibition which was excellent  also visited sacre cour   what was seen was excellent  the downside of paris were the crowds and the litter which seemed to be everywhere around where we stayed
10  1464607321
paris continues to be a welcoming place with much to do and see the people are friendly and the city is very clean
2   1464609708
loved the bars and cafes&#39; great to sit outside to eat and drink easy to getb around paris but it is big and would recommend taxies went on a sightseeing visit in an open top 2ccv great
23  1464612260
so much to do so little time  one should visit paris at least 10 times in a lifetime  i&#39;ve been there for 6 short stays and discover new things and places all the time  doing it all in one go would be totally overwhelming and one wouldn&#39;t remember half of what you&#39;ve seen  paris calls you backagain and again and again!
13  1464612931
i took my eldest daughter on a micro-trip for her 18th birthday  i fully recommend taking a big bus tour with additional trip on the river seine to get an idea of the famous sites and locations  these are very well run regular and easy to hop on and off at your own pace  we were there for 24 hours only and managed an excursion to the top of the eiffel tower which can&#39;t be missed we went for a twilight visit and were very lucky with the weather and could see for miles  we will definitely return for a longer period and the ease of using the rer and metro trains from charles de gaulle airport i highly recommend if you&#39;re not driving over
98  1464612948
the city is not so friendly for children a lot of stairs in the metro train station the best destination is eiffel tower and disney land
16  1464615439
paris is obviously a beautiful city loads of attractions and places to visit half hour away from disneyland on train however there was alot of homeless people&#47; refuges sleeping rough with little kids and begging also alot of pick pocketing going on transport around paris was fairly good although i found it quite expensive
76  1464615782
lots of historic places that you can visit eiffel tower and the arc are pretty impressive notre dame church is not to be missed     we use uber a lot and it is easy to go from one place to another      shopping is also good  it&#39;s just paris is an expensive city so be prepared      restaurants are for me not too accommodating waiters and managers are rude always asking for tips well if their service is excellent i would tip them well i hope that you don&#39;t encounter this like i did to be on the safe side check the restaurant rating before entering via yelp
58  1464617014
love paris!
36  1464617802
good underground system  great architecture and sights
18  1464620433
paris was fantastic i bought a 3 day paris pass which gave me information on (and access to) heaps of museums (egthe louvre musee d&#39;orsay (my favourite) notre dame (tower and crypt) musee des egouts de paris (paris sewers - very interesting and a little smelly!) centre pompidou - and these are just a few of the museums included in the pass)  also included in the pass were the arc de triomphe montparnasse tower a trip on the seine with bateaux parisiens (this is a great way to get a different perspective on this city) a one day hop-on-hop-off bus tour a ride on the petit train de montmartre (great for getting an idea of where all the key attractions are located in montmartre) and a 3 day metro pass which i made good use of as i travelled everywhere by metro
2   1464622625
we bought a batobus 2 day pass it gave us perspective as to where to find what places of interest next time we will do the bus tour we walked almost everywhere when not on the hop on&#47; hope off batobus we visited almost all the famous places of interest
38  1464623683
the paris pass is definately the  way to go when  visiting paris excellent value for money we used roissybus to get from cdg airport for 7 euro each and it dropped us around the corner from our hotel only 3 mins walk away metro line on our doorstep with the opera also on our doorstop we found a fantastic restaurant within walking distance from our hotel (st petersburg) which i recommend as we were helped with the local menu and the staff were great  i strongly recommend that you plan your visit  beforehand and having the paris pass gives you loads of ideas and saves a lot of time the maps are very clear-  the palace of versailles is a must if you visit the louvre you need a full day as there is so much to see -the notre dame and parthenon are well worth the visiti would also suggest doing a hop on&#47;off bus as this gives you an idea of where everything ismake sure you have good footwear as we walked al over paris and its very tiring  we found the parisians very welcoming and friendly and appreciate you making the effort to speak french even if it just bonjour and merci
35  1464633188
paris is a wonderful city and full of life
57  1464635662
the location of the hotel is it`s biggest asset easy walk to lots of good restaurants &amp; bistros as well as to convenience stores along faubourg montmartre there are restaurants which locals visit and not only touristy places  the close by metro station allows access to museums( centre pompidou) highlights marais and shopping st germain easy access to gare du ÃƒÂ¨st &amp; there are also transfers to airport at very affordable prices
36  1464636535
the metro 3 day pass is great for getting around but there is a lot of walking
58  1464637170
shakespeare bookshop close to hotel    musee jaquemart andre - good for exhibitions and its lovelty salon du the  also muse de luxembourg -small but good  also mueum arts et metiers for all science  and much more
34  1464638209
on of the best cities to eat in the world
25  1464648304
shopping
32  1464671230
location is good and staff is helpful
95  1464678273
not overcrowded due to lovely wide streets lots of greenery great historic buildings and a david garrett concert at the champs ÃƒÂ©lysÃƒÂ©es theatre the reason i went to paris! pleasantly surprised i usually avoid big cities really good easy to use metro system and cheap180 euro tickets but the busking entertainment on board and at the stations was that good it ended up costing us a lot more! but worth every penny
42  1464682820
lovely place to visit more
66  1464683729
it is a beautiful city with a lot to see and do (even in the rain!)
2   1464684122
liked the city most at night while walking by the river stay away from eating at the tourictic areas the restaurants around there are dissappointing try exploring to stgermain or le marais
39  1464684770
ok
54  1464685450
paris is too busy these days
81  1464685804
may is the wettest month in france - not the best time to visit but paris is always beautiful and the metro is easy to use it&#39;s also very easy to walk around the central areas - marais left bank isle de st louis etc  anywhere is good to eat french food is wonderful
36  1464686583
a good city but be careful about the theives and local frauds which are of very high in nature now
26  1464686620
it&#39;s the city of   love and dreams
70  1464686919
))))))
63  1464687045
beautiful city people are a lot nicer than i was lead to believe
62  1464687097
the metro location is in front of the hotel  there are direct trains from here to reach eiffel tower
8   1464687787
i travel to paris almost annually for congress it is always pleasant after all it is paris the city with its own charm and character getting pricier every year though  it is a must visit place at least once very easy to move around public transport is excellent for a solo traveller sit by the road side pavilion restaurants along avenue de champ elysee in the evening for dinner is the thing to do
67  1464687964
beautifull city!
82  1464687979
i&#39;ve been to paris many times &amp; each time i discover new areas &amp; experiences to match  this was my first time in the marais district i always enjoy the ambience of parisian life   i love the isle saint louis area experienced great dining last trip but nothing outstanding this time
86  1464688202
du jardin du luxembourg ÃƒÂ   la rue soufflou -place du panthÃƒÂ©on en descendant vers la place saint michel et place st andrÃƒÂ© le parcours est fÃƒÂ©erique manger franÃƒÂ§ais face au jardin du luxembourg ou vietnamien prÃƒÂ¨s du panthÃƒÂ©on prendre un cafÃƒÂ© sur la terrasse du dÃƒÂ©part st michel et le sÃƒÂ©verin est un vrai moment dÃƒÂ©licieux ouf la vue de le palais
0   1464688388
i loved the city its vibrant n lovely lots of historical places and great museums
6   1464688912
paris is so lovely except for the parisian people !    enjoy this city as tourist and never live in it ! trust me i&#39;m french !
99  1464689767
top museums restaurants and bars so close to our hotel
31  1464689789
restaurant  albert located at 7 rue jbpigalle 75009 is a great place for a dinner for 2 with good prices
3   1464689824
we spent 3 days in paris for our second honeymoon we went to the louvre latin quarter and along the siene we ate in le royal cafe where the staff were so friendly and put the football on for us
70  1464689897
wonderful place to visit the city for a week
87  1464689965
great city too bad we couldn&#39;t stay longer maybe next time Ã°Å¸ËœÅ
26  1464690209
very nice
60  1464690487
very busy
62  1464691570
nice romantic city for couples
11  1464692061
we just love the city and have been there many times and will return many times to come
53  1464692392
fantastic city so much to do and see magical buildings and history everywhere the people are lovely and helpful!
27  1464693378
liked everything
82  1464693941
i liked everything in paris atmosphere so different!but trash in the streets for this kind of city too much i think clean service should work better
26  1464693961
the river cruise the line for the eiffel tower was too long  stoppage of issue of tickets to the top was not informed
23  1464697099
great architecture food and galleries
11  1464697678
beautiful city lots to do and see transport links good
27  1464698608
some areas were dirty lots of smokers all the well known icons we really enjoyed we did a lot of walking  taking in all the sites which was really good the train system was excellent drinks were quite expensive   loved every minute of our stay
3   1464698661
paris is an open museum really i enjoyed but touristic bus need to be extended at night to help visitors to enjoy paris at night
81  1464699272
many attractions and great public transport system but the most famous attractions are usually crowded with people so should plan your journey well
79  1464700050
great spot!
51  1464700362
once you get use to the metro it is very easy to get around!   beautiful city!
50  1464700394
one of the most beautiful cities in the world
52  1464700558
superb architecture
0   1464700598
too much rain
18  1464700636
amazing city with so much beauty to see and take in! but an expensive city as well so make sure you take enough money!
25  1464700813
wonderful city to see and explore very expensive though we brought a back pack with us and had snacks as we toured the city which saved us a lot of money indeed
36  1464700920
i really loved the architecture and ability to use the day pass on both the bus and metro i didn&#39;t like the fact that there were too many dodgy looking people who were most likely planning to rob you
37  1464701118
found eating out very expensive sightseeing bus hop on and off is good value and great way to see most sights pre book tickets for major attractions queues are horrendous
62  1464701189
fantastic
92  1464701401
incroyable!beautiful city great to walk around not the expensive place as sometimes portrayed
11  1464701409
i did a bicycle tour (blue bike tours) which was excellent and gave me a good overview of the city  not keen on the louvre just too crowded too busy  river taxi batoboat was great hop on and off all day if you like  metro was easy to use  just make sure you know which line and the end station to make sure you are going the right way  la philharmonie de paris  goodness what a strange looking concert hall good inside though be careful which seats you book asviews can be very restricted by person sitting in front!lots of beggars and rough-sleepers
84  1464701445
didn&#39;t do a lot sight-seeing due to time but really recommend roland garros
92  1464701734
dirty and aggressive
42  1464701801
great trips to the opera for both opera and ballet  wonderful museums and galleries  most people speak english  better than my french  travel in the city easy and pretty cheap compared with other major cities  good food
70  1464701878
lovely city safe clean and beautiful!     see everything!! use the metro which is so simple to use
2   1464701938
paris is a beautiful place - shame the french live their if you&#39;re happy to deal with arrogant illiterate money grabbing egotistical humans - this is just the place i could recommend 1000&#39;s place where people more refined and have better taste
98  1464702306
champagne at top of eiffel tower was magical  atmosphere and food at latin quatre was excellent  metro easy enough to use walked most places
35  1464702391
boat trip from eiffel tower was perfect !
20  1464702528
fantastic city
77  1464702549
everything in paris was very good eiffel tower was very impressive as was everything else travelling for the cdg airport to paris bercy was a fixed rate of 50 euro and was easy to get to restaurants were ok nothing special however i went to cheaper restaurants as i am a student
45  1464702675
i would recommend to climb the eiffel tower at night it is a lovely view of paris when lit up  i also enjoyed a trip to disneyland  notre dame is my favourite place there- it is beautiful    food wise- you have to try onion soup and crepes with caramel sauce    the easiest way around is to use the metro which runs frequently til early hours of the morning
94  1464702684
amazing 2 night bank holiday trip; even the weather couldn&#39;t dampen our spirits would recommend getting a paris street map and using it to detour down the various side streets as this is when you find the hidden tourist free paris with bars and restaurants that are a lot cheaper  however one &#39;touristy&#39; thing i would recommend is dinner at 58 eiffel tower restaurant which was just stunning!
92  1464703001
absolutely loved everything about paris!
13  1464703019
excellent comme d&#39;hab!
86  1464703181
beautiful romantic city with loads to do and see definitely a must on the bucket list
35  1464703226
it is very nice city it is better when organised in advance and every attraction requires long time to see it properly especially the louvre
71  1464703313
paris is dirty
20  1464703328
france (paris particularly) is losing its identity far too may ethnic groups hanging about every shop bar eatery supermarket; just loitering on every corner etc  this is not a racist comment just a fact
92  1464703727
great sightseeing magnificent buildings &amp; chance to see history food very good &amp; reasonable in price would highly recommend getting a 3 day pass for hop on hop off buses to get around the city
99  1464704152
street cafes montmarte louvre musee d&#39;orsay excellent
34  1464704659
easy and quick to get around on the metro (regular service until late) montmartre is a must-see for fans of the film amelie (though watch out for the street artists) galleries lafayette is a veritable shoppers paradise and if you are lucky enough to catch a recital&#47;performance at the theatre des champs-elysees (an art deco gem in the avenue montaigne) enjoy!
82  1464704728
most ignorant people i have ever met awfull people
56  1464705657
quay d&#39;orsay art gallery is a favourite  it is a nice place to walk about
11  1464705854
be careful in paris; not a safe city
7   1464705870
paris was wonderful just wish we could have had longer to soak up the sights
19  1464706098
i like the efiel tower the most and had a fantastic vietnamese meal at panda belleville the prices of the drink at the hostel are a bit expensive 8Ã¢â€šÂ¬
87  1464707348
amazing city
47  1464707940
paris is beautiful no problems getting around and plenty to see and do we ate dinner at the montparnasse tower which has a stunning view of paris and the eiffel tower and the food was very good in hindsight it would have been better if we&#39;d booked a table later in the evening to ensure seeing the lights at night  french people can be quite rude but that would be my only complaint
56  1464708625
i didn&#39;t have enough time to see paris because of a pilgrimage to chartres so i&#39;d like to return to get a better feel for it
8   1464708768
a very romantic city lots to do and so many wonderful things to see
69  1464708960
paris is so elegant it has everything in terms of impressive art beautiful buildings and memorable landmarks it&#39;s relaxed and easy to get around    most museums&#47;monuments are free to &lt;18s- a big bonus if visiting with kids
18  1464709185
vibrant varied city!we love it and will be back again
60  1464709202
people were kind and accommodating
45  1464709920
i love paris when there are no strikes
58  1467102494
do not miss these in paris the sacre d&#39;coeur in monmartre is absolutely stunning and walk around the local streets afterwards to check out the famous painters corner and have lunch in one of the funky cafÃƒÂ©s and later that night head to the moulin rouge climb the eiffel tower don&#39;t be put off by the crowds the lines move faster than they look and the views and experience will stay with you forever make the 30min trek out on the train to versailles it will blow your mind but go early to miss the crowds and prepare to spend a day there if you are into art def go to the lourve but it will require many hours to get through we like the musee d&#39;orsay better its smaller and easier to navigate with great artworks on show
86  1467106122
where ever you go you will find something interesting in paris get a free view of the city from the roof top resataurant of galeries lafayette or from montparnasse tower take a boat ride on seine river! eat in the latin quarter take a metro or get a map of the buses and get the view while on the bus if you are into sports (i visited tennis on rolland garros) or art (i would recommend smaller musee d&#39;orssay) you  have lots to choose from even traveling alone i felt safe all the time
23  1467106210
food was excellent   paris architecture worth exploring
89  1467106625
i was born in paris it is a magic city
5   1467106631
paris is a moveable feast
78  1467107068
it&#39;s just paris ! fabulous city with beauty and grundge side by side and history everywhere walking the streets is the best way to absorb the delights of this city and its people
89  1467107156
paris was overall a good place liked the scenic beauty around paris   problem comes with communication french people don&#39;t like to speak or communicate in english it was a bit difficult to explain few things but managed! phew!
92  1467107401
picture perfect!
51  1467107567
beautiful parks and museums
4   1467107822
opera
12  1467109007
it&#39;s beautiful!!!
12  1467109506
very exciting
15  1467109681
the green high path starting at bastille was very nice
95  1467110155
paris is fabulous!everyone on earth should be allowed to visit there at least once in their lives
64  1467112132
we loved paris for all the wonderful sights and the history of the city we were very excited to see the eiffel tower notre dame the arc du triomph and the mona lisa at the louvre
33  1467112161
amazing city but very pricey don&#39;t expect to pay less than Ã¢â€šÂ¬8 a pint of lager that is the cheapest we found the tube system is perfect get a zone 1-5 visitors pass from the airport and you can explore everything very easily
1   1467112641
subway is confusing but i could get it if i stayed longer lovely city but very expensive
53  1467112774
it&#39;s a big city everything is very expensive it&#39;s easy to get around we used metro all the time
15  1467112784
paris is as always stunning  go walk &#47; get lost &#47; discover it for yourself  don&#39;t believe the stereotype parisiennes are amazing if you are respectful and try out your french :)
7   1467113650
i travelled during the floods but paris continued to be an exciting city to visit
80  1467114028
went for the euro 2016 tournament most of paris is dirty and in need of repair  no need to rush back there
23  1467114057
very easy to get around paris with the metro or by foot   lots of great places to eat  i love to eat at vapiano  laduree brioche doree paullafayette gourmet&amp;starbucks lafayetteeclaires de genie maison du chocolat and i can say every bistro&amp;restaurant is unique and can offer lots of good plates   the attractions are so many that you&#39;ll need to be very otganized to see them all or stay longer the view from sacre coeur&amp;arc de triomphe is amazing(i was there ) and i also recommend you to go up montparnasse tour eiffel(you have to wait a lot or buy online tickets ) and notre dame
19  1467114256
great place!  we found a boat called batobus where you pay a daily ticket for 11Ã¢â€šÂ¬ and you are able to navigate across the seine and stop several times right in front of the best touristic attractions like eiffel tour
27  1467114718
ginette&#39;s bar and restaurant next to hotel adonis roma sacre coeur was a true delight lovely staff especially vanessa: great food and atmosphere
87  1467115355
take the tour bus around the center once use the headphones  then on the second go around jump off where you want too great restaurants  great sites to see  friendly people (please be careful with pickpockets on metro) just amazing city to vist
66  1467115649
love paris impossible to review as i go there probably once every 6 weeks the city is filled to the brim with different awesome areas and i&#39;m just trying to get to know them all
49  1467115826
if you are mobile it is very easy to walk around paris to the main attractions the big wheel in paris is brilliant for views and not too expensive at 12 euros per adult the louvre is good value at 15 euros each per adult don&#39;t have to pay for under 18&#39;s students between 18-25 with valid id or oaps eating in the open air cafes in the rivoli gardens (in front of the louvre) under the trees on a hot day is lovely  if you are into shopping the galeries lafeyette are a must see as beautiful inside (expensive tho) go to the top floor terrace for great free views over paris also printemps terrace on top of the homewear department has views there is a terrace cafe there but you don&#39;t have to use it to enjoy the views luxembourg palace gardens are also beautiful
3   1467116980
what a massive city i love paris coz it has such a character !high sense of fashion crazy sports cars and beautiful things to look at nice food but the traffic and road discipline is questionable sorry for the poor slum dwellers making ends meet
24  1467117688
amazing city with lot of culture sightseeing art good food and shopping
39  1467118420
everybody knows paris the only thing wrong with it is it&#39;s expensive and the odd rude waiter but to be fair that&#39;s the exception     recommend akasaka for sushi and la librairie for modern french food oh and tokyo eat for a nice al fresco lunch with a view   if you&#39;re on holiday walk everywhere it&#39;s not that big you&#39;ll see more of it and it&#39;ll do you good
57  1467119276
amazing
51  1467119473
good people helpful nothing too much trouble just look out for spivs around tower
32  1467119486
fantastic city great buildings and culture
69  1467119628
didn&#39;t realise it had so much to offer will come back for sure  loved it!
11  1467119650
were down for the night for the wales game people were polite and friendly metro gets you around with ease would recommend day tickets spent most of time in lovely restaurant called mimmo&#39;s i think it chatelet
98  1467119961
disneyland and effile tower are awesome  whenever you are visiting to paris please take care of your belongingsenable international data service on your smartphone to search popular attraction and map as you find too much difficulty with language
79  1467119996
walking around early in the morning
39  1467120153
expensive but worth it
78  1467120297
i didn&#39;t have much time in paris which was unfortunate - it really is a fascinating beautiful city that has much to offer and lots of different places to explore it feels less claustrophobic as some other major cities i&#39;ve been to (eg london); staying away from typical tourist haunts helps as well i&#39;ve been pleasantly surprised by the centre pompidou which has a huge collection of modern and contemporary art as well as multiple temporary exhibitions and is definitely worth a visit any time! though people say that paris is an expensive city it is perfectly possible to eat out perfectly affordable: try side alleys that are not as frequented by tourists so much and look for smaller cafÃƒÂ©s brasseries or bistrots we had a three-course-meal in one family-led cafÃƒÂ© close to saint-ÃƒÂ©tienne-du-mont for 1650 eur including a glass of wine and excellent (!) food getting around is easy enough as public transport leads you virtually anywhere; i&#39;d also recommend going around by bicycle - there are countless of bike stations all over paris where you can rent bicycles this is especially recommendable on sundays when some roads are closed off to cars and such
80  1467120363
beautiful city friendly and welcoming people so much history and amazingly clean for a capital city bars and restaurants were brilliant
56  1467120415
one of the most beautiful cities i have ever visited spectacular views from everywhere you are we visited disneyland on the first day of our visit and it was one of the most memorable days ever spent the rides the shows disney characters and the most spectacular lights and fireworks show that you will ever experience do visit here even if you have just 2 days in paris we visited the eiffel tower the louvre notre dame and the arc de triomphe on the second day of our visit try visiting the eiffel tower early in the morning to avoid extra long queues also do visit the eiffel tower at night even if its just from down to see it light up to glory
90  1467120801
paris is a beautiful city full of history and interesting places to see always busy with a great vibe its such a pity it is ruined by lots of vagrants gypsies and families with children living on the street dog mess on the pavements i was truly dismayed by a lack of civic pride the traffic is never ending with noise horns and sirens constantly happening however it is a great place and i will return
8   1467121436
lots of things to do however the lines are very long it&#39;s always 1-15 hours of waiting the catacombs versailles notre damn eifell tower prepare to stand in a lot of lines for many hours
48  1467121446
easy to get around &amp; cheap on metro food is not the best &amp; if your going out to drink in the bars take out another mortgage!
4   1467121525
the metro is fast and frequent but has lots of steps not for the agile  the buses are great and if you can use the bikes you get extra points for going up hills  enjoyed a meal at the triumph at nation  the picasso museum was worth a visit but i am a fan of picasso  be cautious - re beggars&#47;homeless its best to give to charity don&#39;t get your money out or you may be mugged
74  1467121701
paris was a great city to visit and we wished we could have stayed longer
71  1467121751
i&#39;ve been to paris several times but there&#39;s always more to see and do great food and lovely people we were there for the euro 2016 football but had some extra time to see the sights notre dame at evening mass is magical
34  1467122055
the place is great for shopping art culture fine dining and people watching
96  1467122281
such a massive city with loads to do and see
6   1467122435
i went for the first time in my late 50&#39;s - don&#39;t take as long as i did great city
60  1467122559
went to watch wales play northern ireland in the euros great atmosphere in the city!!
39  1467122629
the louvre museum collection was very good  we are vegetarians  but was difficult to find the veg food around  only pizzerias to be located  it is easy to get around paris take the suitable pass  we were in disneyland louvre eiffel river cruise notre dam   walked a lot places are too crowded especially on weekends
93  1467122750
great city with plenty to do
73  1467123460
great city!
76  1467123619
superb city two nights is not long enough to visit
56  1467124482
footy fan zone
44  1467125108
hop on hop off bus definetly the way to see the sites always a cafe on every corner for something to eat and drink
96  1467125281
attended euro 2016so was not there as a tourist so to speak    lots of fine restaurants nearby  turn left out of hotel and a fine restaurant called &quot; le fregate&quot;
81  1467125699
haven&#39;t been back for a few years but the city is just as beautiful and frenetic as i remembered - especially when france win at football
83  1467126400
site seeing is great but the city has become over crowded
40  1467126507
it&#39;s paris!
46  1467126960
to expensive
31  1467127449
easy to get around
88  1467127827
lovely city but as with any city you need to be wary of confidence tricksters vagabonds and pickpockets
76  1467128003
paris was dirties city i have ever visited in the europe
80  1467128595
the tour bus routes are the best way to travel to see the sights
45  1467128890
book everything in advance - makes it much easier and quicker
68  1467129794
visiting the eiffel tower was great and also the open top bus we stopped at a restaurant  which was also great
28  1467129860
loved our stay in paris  there is so much to do and see
41  1467130272
metro system makes getting around paris easy
34  1467131641
the ideal weekend break for those looking to enjoy plenty of culture and architecture
78  1467132153
a lovely city to visit with things to do for everyone
69  1467134183
the best in the world - in many ways
62  1467134202
favourite place to eat champs de mars bistrot eiffel tower was amazing especially at night do the hop on hop off tour bus best way to see the majority of paris
17  1467134228
loved everything we saw such a beautiful place metro very easy to travel around
41  1467134627
ate at les deux magots  enjoyed a delicious dinner
75  1467134975
you can walk to alot of places study the metro routes before you go saves standing around wasting time working out routes take a moment to get the right tickets you can pay by credit card saves carrying a load of change get tickets in advance for the likes of the eiffel tower there will be queues at every attraction don&#39;t try to see everything you will be exhausted if you do space it out take time to sit in the parks they are beautiful
1   1467135052
the city has lots of great places to visit notre dome had a beautiful mass on sunday  in the evening the city is alive until very late  several dining options hard to select where to go
1   1467135074
far too expensive and the locals seemed annoyed that we were in their city
23  1467136094
amazing city always something going on and lots to see and do the festival of music held once a year in june is a must
20  1467136503
definitely a place for culture and fun
11  1467136507
need to be careful where to eat and drink due to cost but great for a visit
9   1467136575
a great city with much to do check out who&#39;s on at the accor arena i managed to get tickets for celine dion
4   1467136655
food really expensive   transit really good  tour bus was good
51  1467137213
paris is a very beautiful city the traffic is crazy the transit system is great  food is good but i am used to an bigger type of breakfast and found breakfast lacking it would be nice if there were more non bread items to eat there is no lack of things to do and see  i did not go to a mall and i found shopping on the champs-elysees disappointing people are very friendly and tolerant of my lack of french vocabulary  all in all i was very satisfied with my short visit and would definitely return to check out the things i missed  travelling alone was fine lots to do and people made me feel welcome
69  1467137591
paris is beautiful and the people are super friendly  must see- the lourve (you could spend days in it)  eiffel tower notre dame saint chapelle arc de triomphe so many places to visit  highly recommend hop on&#47;off bus fantastic and easy way to see the city  paris is a beautiful city and a must do for everyone
3   1467138357
there for the euros football
23  1467138560
all the tourist places are well worth the effort the eiffel tower is very impressive and also the interior of notre dam the boat trips along the seine are also worth a try hop on and hop off which saves the legs!
35  1467139467
very easy to get around on the metro and tickets are cheap and easy to buy views from the top of the eiffel tower are breathtaking on a clear day food and drink is quite expensive i would recommend walking from the louvres to the arc de triomphe through the gardens to see a lot of the sights all in one go!be careful for pick pockets there are a lot of beggers on the streets that can get quite close at times when we were on the train to central paris from the airport a chinese couple had their bag stolen by a man who walked on the train sat down behind them then grabbed the bag as the train pulled in to the next stop and ran off the carriage
52  1467139688
there for euro 2016 with the green and white army!! football trip so not much sight seeing will griggs on fire your defence is terrified !!
72  1467140649
still one of the best cities in the world to visit
68  1467141368
we were only in paris for a day and night and didn&#39;t have time for sight-seeing but it&#39;s a great city from previous visits
39  1467142243
too many immigrants looking for trouble
0   1467142439
love paris!
94  1467142457
bateau parissiennes boat trip was a bust  english phone guide not working and we could not hear a french guide     loved musee d&#39;orsay!
11  1467142827
awesome spots
30  1467143297
fantastic art galleries great relaxed atmosphere good food a shame that our trip coincided with both the brexit vote (which put a damper on our mood) and the euro football which meant the eiffel tower gardens were all fenced off despite all this we had a lovely weekend and found the people of paris very welcoming the monet water lillies paintings at the orangerie were incredible i&#39;d also recommend a visit to the cluny museum of medieval art - especially if you&#39;d like to see the original versions of the harry potter gryffindor common room tapestries (the lady and the unicorn)
36  1467143712
paris is a wonderful city to visit the seine was flooded when we went and all the museums closed but we walked around town all day and it&#39;s so easy ! with sights all close together excellent food and drink of course and the very best service  always
52  1467143852
the louvre is a must of course buy admission tickets (and audio if wanting) in advance to avoid waiting any any lines the l&#39;open bus tour was a great overview of the major attractions
74  1467144789
i only visited paris for one night after a sports event but it did not encourage me to come back for longer trip overcrowded expensive full of beggars - in one word overrated  the only nice thing were good food and coffee in independent cafes
20  1467145826
went with a group of friends for the football far too expensive won&#39;t be returning again
6   1467145845
viva gareth bale
80  1467146399
amazingly beautiful city
91  1467146743
easy to get around  friendly city
76  1467147750
what a wonderful city so much to see and do ! top tip don&#39;t use taxis they are far to expensive  use uber app the savings are incredible ! more to spend on food and excursions !
28  1467147948
great city many museums good restaurants  convenient metro
60  1467148699
be careful of mutes bearing petitions!the police saved me from a rima ripoff by two kids
86  1467149625
short trip for football metro easy to use but not clear when stop at night so not go to far afield on 1st night recommend checking and planning first
91  1467151970
sacre cour
36  1467153501
it was easy to get around via the metro some great places to see and any amount of restaurants although it can be pricey
93  1467169631
only stayed 1 night for connection purposes so no time for sightseeing
81  1467170336
nice place to visit at least once need at least 3-4 days 2 days for louvre alone if history interests youcould visit again
75  1467177017
beautiful city very easy to get around great atmosphere - lots to do
29  1467180534
beautiful city but now getting too busy
44  1467181853
great city so much to see and do good transport links to all attractions a must do for those who love citybreaks
16  1467182732
better than london
49  1467183829
good location in centre of city lots of cafes and small shopping outlets near that apt high speed wifi was best part and nice clean and small apt on 2nd floor with no lift and small staircase so we had to carry our luggage ourselves to 2nd flr apt safe locality and nearby bank and supermarket which was open till 11 pm  overall great except small rooms where we could hardly open our bags on floor and little suffocation as it did not have cooling so we had to keep windows open at night for ventilation
58  1467184672
paris is very easy to get around as metro is similar to underground network in london  metro staff are helpful
52  1467185066
notre dame  museum louvre  eiffel tower arc de trioump and sacred heart church at montmatre but too many pickpockets at 18th district
54  1467185717
perfect of course and so well prepared for international visitors from the taxi drivers to the restauranteurs love it
47  1467185744
torre eifel notre dame chams elyse and others places
1   1467186768
i loved paris it&#39;s culture food history and people all very welcoming  even the train strikes didn&#39;t present too much of a problem as they offered us alternative tickets to get us closer to our next destination i could easily live here
53  1467186825
lovely city with fantastic sightseeing place and trendy shopping area
36  1467186858
le petit chatelet l&#39;isolotto and cafÃƒÂ© de notre dame was great  wunderful strolls around the city
14  1467187256
a great place to go to see wales football team win
3   1467187373
eiffel tower notre dam church champs-elysees galeries lafayette are our favorite places to visit enjoyed our stay it was easy to go round enjoyed river cruise
39  1462484867
just get lost in the city you never know what you&#39;ll find
26  1462501207
Ã¥â‚¬Â¼Ã¥Â¾â€”Ã¤Â¸â‚¬Ã¥Å½Â»Ã§Å¡â€žÃ¥Å¸Å½Ã¥Â¸â€šÃ¯Â¼Å’Ã¥Å½â€ Ã¥ÂÂ²Ã¦â€šÂ Ã¤Â¹â€¦
73  1462520352
we were pleasantly surprised with paris and had expected it to be far more expensive we travelled by metro and just did the usual sights  they have a fantastic audio guide in the louvre i would highly recommend this
91  1462520855
great public transportation with several ways to get anywhere
31  1462520984
lots to see and do great history and photographic opportunities easy to get around on the metro
86  1462533242
a great old city with museums and lot of places to see
50  1462533369
beautiful city plenty to do and see easy access metro
93  1462546500
very historicpicture perfect and worth all the money
28  1462548994
it is very nice town i will come again!!!!
27  1462552538
trip on the seine very good  hop on hop off bus good  paris itself was dirtyrestaurants often closed  service was often poor
9   1462562660
the most visited city in the world with good reason a bit dirty and second hand - but she&#39;s been through a lot enjoy and expect to wait for many of the attractions in the high season - well worth the museum pass and the skip the line options the &#39;hop on hop off&#39; bus is the best and cheapest and most interesting way to get around to all the major things
58  1462566610
paris is always beautiful walking in paris is the best way to see the city and every street is a joy
90  1462579722
always paris is paris
74  1462584423
trains were amazing 1 every 2-5 minutes exits can be a bit tricky  loved the montparnasse area we stayed in
21  1462588221
love the museums and of course  the iconic monuments ie eiffel tower     really a great place to shop for designer goods!!!
53  1462603671
people were helpful and friendly train system easy to navigate attractions well signposted weather was magnificent!you could go budget or high class easily lots of options fantastic place  such history and lavish lifestyles of past citizens
26  1462605104
paris :)
61  1462607050
one of the best cities in the world it has something that you can not explain in words
89  1462608401
our third time in parislove the food and fashionenjoy walking and see the things from different angles!  marseille was disappointed placesneeds cleanup on graffiti on buildings and walls!!  avignoninteresting place must visit again in the very near future!!
40  1462611514
leave a full day for the louvre as it is huge
50  1462613202
we had been reminded several time for pickpocket besides that we really enjoy the trip
43  1462613653
loubnane restaurant in the fifth arrondissement monet&#39;s house in veron-giverny walking in the streets get a navigo card if you will be there more than a few days and try the buses (bus maps from metro stops)
5   1462614368
there is so much to see in paris so allow yourself a few days we went for a week and that was about right don&#39;t set yourself too many objectives allow time just to wander or sit and watch the world go by there are many squares parks and gardens and paris is a very green city    of course it has a reputation for being expensive and it can be but read as much advice on line as you can for instance don&#39;t bother with the various tourist passes just get &quot;un carnet&quot; (10 tickets) for the metro and a quick search even in the popular tourist areas will uncover several bistros with fixed price three course menus for approx 18euro if you are travelling on a budget avoid the wine in eateries the mark up is astronomical tip - if you don&#39;t order any drink at all they will usually bring water for free
75  1462615447
both seen the main sites before so wanted to see other sites at a leisurely pace metro cheap and extensive and off-peak bus a good option too to see what you miss underground avoid cafes facing main sites if on a budget but plenty of alternatives around the corner montparnasse tower a good viewpoint to avoid queues at eiffel but troccadero roccadero good place to see the tower at night
22  1462620549
st michel la seine montmartre le louvre   we often ate in st michel various specialities   we enjoy the ballad by the seine
29  1462621439
we had nothing but good experiences for the whole of our trip
57  1462624159
paris in springtime a great place to be we did lots of walking in various areas such as montmartre saint germain and the marais    we had dinner on rue mouffetard where there are many restaurants to choose from    don&#39;t forget to go and eat the best falafel in paris in the marais there may be a line but the falafel is always worth the wait!
33  1462624742
everything
26  1462626766
things i learnt by visiting:    if you are worried like i was about getting from the airport to the city id say use the roissy bus to l&#39;opera stop this stop takes you straight to downtown and it is easy to get a taxi to your hotel from there i actually just walked to my hotel from that stop also ask your hotel if they have airport shuttles believe me for an extra 10 euros it saves you a lot of hassle esp on your way back to the airport     the eiffel tower is not for those afraid of heights having visited other tall buildings i didn&#39;t think it would be that different but i was wrong the glass elevators were not that large and it feels like you are on a disney ride that won&#39;t stop going up! it is an experience of a lifetime but i definitely won&#39;t be taking the elderly folks     you can spend half a day in the louvre i really didn&#39;t have enough time to wander around as i was trying to see as many sites as possible and the louvre is one of the first stops on the bus tour so i was eager to get to other sites however you should just enjoy some time there    now for more helpful tips:     pre-buy the special ticket to skip the line esp at the eiffel tower and louvre i spent maybe 20 mins for the louvre (on an off period) but the special line was empty and so to for the eiffel tower where i spent about 2 hrs nb this was not in summer also carry a snack even lines for the one or two food stalls were long at eiffel tower and none at louvre    the open bus tour   best investment as you can just hop and off and visit wherever you would like mind you it closes at certain times so you may wish to check this before you get left behind    have fun!
47  1462627757
you have to return :)
13  1462628373
beautiful city a huge choice of things to do whatever your taste   generally a very relaxed vibe despite recent terrorist issues security reassuringly in evidence but not over bearing
6   1462632729
c&#39;est un tres belle ville est un ville dynamique est tres interessant j&#39;adore paris!  check out eiffel tower louvre tuilleries napoleon&#39;s tomb centre pompidou notre dame and catacombs if you&#39;re not claustrophobic
65  1462634616
great cityconvient
88  1462638327
really made use of the hop on hop off buses  able to visit most tourist attractions
9   1462639107
eiffel tower was brill we went to the topmost of the tourist sights were well worth a visit on the open top hop on hop off bus  the metro system is very good to travel all over paris
64  1462639354
paris always offers interest animation and a great ambience  monet&#39;s gardens at giverny was a destination i always wanted to visit and now i have!
29  1462640421
mcd and restaurantsgetting around  difficult due to absence of english signboards and travel guides so is metro
44  1462641468
pickpockets on the subway mean you need to be extra vigilant  be sure anything in a backpack is replaceable as we had two attempted thefts  attractions did not have the long lines i was expecting which was a treat
29  1462641731
with all the gadgets available today at ones disposal getting around paris was easy    the notre dame cathedral and other surrounding churches in the area were awesome      one place that stood out where we would normally purchase our bread was julienne  one must try this place  the flavors combinations and freshness was exceptional let alone the friendliness of its staff    the metro was easy to use and purchasing tickets for it was easy as well
57  1462641993
paris is always a joy even if some things are expensive
3   1462646795
this place is very dirtybathroom stinks and carpet in the room seems to be 100 years oldthey charge for breakfast and extra tax for being in centrak paeispeople in general arrogant towards tourists and they don&#39;t want to speak english everything was 3 times more expensive than rest of the european countriesi never visit paris again!!!
58  1462646975
i like everything  except the food who was very expensive :(
42  1462647288
great but expensive
62  1462651430
greatejust go thereyou will see real europe and worlddirty uk thats not whole world
86  1462654193
missed a flight connection at cdg so took the train to town checked into hotel and walked around for a few hours narrowly avoided getting pickpocketed in front of notre dame cathedral had a great ice cream cone on isle st louis and later got a falafel in the marais district and ate it sitting in the plaza of the pompidoudelicious  then walked over to the louvre it&#39;s open late wednesday and no line or crowds at 8pm finally saw la giaconda
22  1462659563
all the touristic spots are worth visiting besides the popular spots there are areas worth visiting like canal st martin quartier latin centre george pompidou we  could not visit victor hugo&#39;s house because of time limitation! it is absolutely easy to get around in paris by metro after initial trials and errors walking through the streets of paris is itself a great activity
45  1462669190
awesome city! have a nice meal at the croissanterie have wine and beers next to the eiffel tower get lost in paris streets smell the flowers and enjoy this lovely city!
58  1462680678
i have a short period to stay i like this place especially the lurve  great architecture setting around will return to visit again
56  1462684024
just for once in life time
5   1462688917
walked around the city  and for sure you have to visit chÃƒÂ teau versailles
23  1462691775
we loved strolling along the river the eiffel tower and the louve    eating was fairly easy but watch out for the quality of some of the cheap places in the latin quarter  there were lots of great places to have a coffee or a beer and watch the world go by - this was also one of our favourite things to do
87  1462694372
if you only speak english it&#39;s a lil hard it was a great experience going to the eiffel tower the louvre notre dame arc de triomphe the best way for me to go everywhere was on the batobus don&#39;t forget you can get uber way cheaper than a regular taxi we had crepes at happy place very nice people and it was delicious ( it&#39;s close to the louvre park) my only bad experience was with an airline ryanair it was horrible worst customer service ever our flight got canseled and basically they told us to figure it out on our own
9   1462694739
buy tickets from a machine at versailles much shorter queue than the ticket counters
4   1462695734
bad stay bad breakfast bad stuff
56  1462699754
every thing in paris absolutely  fantastic
13  1462700210
st severin at the back of notre dame was a great find of nice inexpensive cafes &amp; restaurants    chatelet &amp; hotel de ville is worth a visit after lunch  for great light
4   1462700253
what an amazing city!!!!
57  1462700383
too much crowded it is almost impossible to see something
39  1462701016
tourist spot were nice people are quite rude and not friendly i went to london before coming to parislondon people were awesome and place were superb so it was total opposite in paris i didn&#39;t feel safe in paris language was a problem too as most of paris people can&#39;t speak english not a good trip to paristotal disappointment
62  1462701381
paris is a beautiful city lots to do and see the people are very friendly and helpful  public transport is excellent  i would definitely recommend paris to anyone even though it&#39;s a bit  expensive
69  1462702710
paris is one of the most stunning cities in the world  it offers everything-from great food to night life to everything  it is a must see !
94  1462703814
paris is the most beautiful city i&#39;ve seen so far i also like milan very much but paris is the city you most see  here is the list of places you most see :  eiffel tower   champs elysee  place de la concorde  sacrÃƒÂ©-cÃ…â€œur  opÃƒÂ©ra national de paris
51  1462705793
great place
31  1462707737
my favourite city in the world the place is absolutely stunning we had a couple of days where we didn&#39;t plan on doing anything which was probably my favourite part there&#39;s nothing better than strolling and exploring this wonderful place i was a little worried beforehand as i had heard the french can be rude and abnoxious this for me cannot be further from the truth everybody we encountered was extremely courteous polite and friendly overall i really cannot wait to go back so much so i was on bookingcom planning our next visit the day after we got home!
25  1462709263
was a pitstop on the way to milan by train so didn&#39;t have time to take in the sights -maybe next time
33  1462714040
the open top hop-on hop-off bus was great - two day pass especially good value  buy a carnet of 10 tickets for use on metro or buses - this is generally better value than buying an expensive travel and museum pass unless you have a massive appetite and stamina for non-stop sightseeing and travel  explore smaller streets and areas a little way out from the centre to find convivial atmospheres  the left bank and marais district are much more quirky than the right bank and the so-called shopping centre  paris offers much better fare than the usual chain stores if you seek it out  the latin quarter has lots of good quality restaurants without the huge price tags  saint germain des pres is lovely for strolling and cafes  enjoy wandeing and watching and keep an open mind
86  1462715556
paris is a fantastic place for wandering around and seeing the sights - the eiffel tower sacre cÃ…â€œur the louvre being only a few of the places you must go to but just perfect for walking around and soaking up the atmosphere
92  1462716928
my only tip is enjoy paris for what it is and make sure you don&#39;t rush round trying to do it all the best things are sometime just exploring the area you are in and sitting back and watch the parisian life pass by  and make sure your shoes are really comfy
43  1462718583
tour bus trips are awsome way to explore paris
73  1462721460
louis vuitton foundation building great to visit
7   1462721494
all good although everything was expensive - partly due to the weak pound
28  1462721706
my family loved all the sites the eiffel tower arch de triumph notre dame etc are worth it  we ate at angelinas known for its hot chocolate but we enjoyed a three course affair pricey yet entirely memorable lovely service and food  we used uber to get around and think their drivers are wonderful for family transport
2   1462722344
like any city it is very beautiful and you and  you can&#39;t do it all we couldn&#39;t get in at notre dame which was our aim because of the queues but it was still beautiful  we had a meal at le train bleu _ magnificent!   lots of things to offer in paris and we didn&#39;t meet even 1 rude parisian  we had never heard of beaullivan (?) ice cream but will beat the queue nexttime
1   1462723334
stayed in paris on a stopover enroute to zurich dumped cases went straight out to have a meal and see some sights had a lovely meal for 10e at place st michel which is easy to get too via the metro visited notre-dame cathedral see at night when lit up as with the eiffel tower as well then had a nice walk along the river seine   a fantastic evening in a great city !!!!
7   1462725686
paris in one word is stunning my fav place to visit anytime during the year recommended i would say if u haven&#39;t seen paris then you haven&#39;t seen anything
9   1462726167
i love paris and have now been there for many short breaks the architecture is stunning there&#39;s great shopping available lots of parks to relax in and of course endless bars restaurants and cafes the city is quite compact and it&#39;s possible to see most of it on foot also it feels as though people actually live there as with many major cities it is sometimes quite hard to find good food at a reasonable price so a little research helps but most of the time it&#39;s just luck i would advise trying the smaller backstreet restaurants rather than those in the main tourist areas especially those near sacre coeur there&#39;s lots of tourist stuff to do but when you&#39;ve done all that there is always more to experience
54  1462726519
montmartre great to wander around and go to a cabaret show at night moulin rouge expensive try la nouvelle eve instead Ã°Å¸â€˜Â
43  1462727536
i highly recommend going on a tour through pariscityvision! we were only in the city for one full day so we chose to go on their &#39;paris in one day sightseeing tour&#39; we got to visit the eiffel tour the louvre and notre dame cathedral and got to the front of the line the whole way through our guide was very informative the cafes and dining in paris was fantastic - i need to return to experience more of the city one day!
72  1462727633
i&#39;ve been to paris before so i knew what to expect  we decided to stay outside the city centre and this was a good choice as the area we stayed in was lovely and quiet  it&#39;s so easy to get around paris as there was both a metro and rer station within walking distance of our b and b and buses also early may is a great time to be in paris before the bulk of the tourists arrive and it wasn&#39;t too hot  i can&#39;t wait to go back
27  1462727714
almost everybody knows the places of interest but the best thing in paris from my point of view is that you can go everywhere in almost any direction and you&#39;ll see beautiful and interesting places and all the city looks like one big museum
77  1462729806
you can go all out and do everything in terms of shopping or sight seeing or you can do what i did and walk the streets enjoying the ambiance and the sights  i really loved the luxembourg gardens and the other small parks i found along the way
82  1462730202
paris is very beautiful - there is so much to see that is incredible we used the boats on the seine to get around a lot of the time walking in between so many wonderful sights are a short distance from the river  we think it is great that 10 euros buys a ticket from any metro station in paris to cdg airport no problem  we ate in lots of lovely places but the cafe in musee d&#39;orsay behind one of the big clock faces was wonderful
94  1462731053
paris is beautiful but the traffic and exhaust fumes downtown caused us to feel sick     almost everything ( museums etc) in paris is within walking distance - 30 minutes     if one has a museum pass in hand access to all the museums is fairly fast the louvre had the longest line ups    best lunch we had was a tangine at a moroccan restaurant in st germaine  best dinner at a tiny french restaurant near montmartre - au gourmet
49  1462731548
we have been regular visitors to paris for many years and walk there a lot with the aid of &quot;time off - book of paris walks&quot; over the years it has taken us to parts of paris that we would probably not have visited    the proximity of the hotel to two excellent bistros chez leon and le p&#39;tit canon is a great recommendation but we always pay a visit to chartier
16  1462731571
i was just on a one night stop-over in paris the area around montmartre is very convenient and i felt safe walking along the streets even at night and early morning    you could take a walk from the hotel to visit basilica sacre-coeur and enjoy the beautiful breathtaking view of paris as well as to view inside this historic basilica along the way there you can pass by moulin rouge le moulin de la galette place de tertre and plentiful of eating places around
84  1462731677
we had 3 full days in the city with a 4 year old  we did it i do think it would have been better with maybe another full day in the city so we would have had some time to rest but we did everything on our list and then some
95  1462731788
lots of sight seeings
17  1462732989
with the location of our hotel getting around paris was a breeze  we were very close to gare du nord (we arrived by train) and the subway system is well marked and easy to use    the museum pass really helped reduce wait times and was more than worth the price  the eiffel tower is best seen at night - wouldn&#39;t bother going again in the day and it is probably best viewed from the trocadero approach    we loved the small restaurants near our hotel in the marais - didn&#39;t need to go far to find find food and having all the menus posted outside makes choosing much easier
46  1462733010
always enjoy paris musee d&#39;orsay great
67  1462733590
i loved the disney world and palace of versalles
97  1462734835
don&#39;t plan paris let it happen!
44  1462735316
be careful of pickpocket for my wife case the pickpocket are 3 very young and decent looking girls you will never expect they are pickpocket if you met them on the street so keep some distance from any youngsters who approaching you for any reason there! if possible always carry your backpack in front of you especially in the metro and crowded area    on a bright side i am very impressed with how friendly french people is my wife and i are both asian and couldn&#39;t communicate in french however in numerous occasions local people just walk over to offer their help without us asking some of them can&#39;t speak english very well but they still try their best to help! thanks to these group of wonderful people who made my visit to paris a superb experience (which also overshadowed the aforementioned nasty &#39;pickpocket&#39; incident!)
52  1462735379
everything
79  1462735979
love the architecture pastry shops seine boat trips ( particularly night time on calife) eiffel tower just love parisÃ¢ÂÂ¤Ã¯Â¸Â
43  1462736290
wonderful ambience
24  1462736319
enjoyed the louvre and a trip to the eiffel tower and we were lucky the weather was beautiful  !
3   1462738572
i had not visited paris for a long time and always found it too busy and generally unpleasant this time it was with different eyes that i experienced paris the eiffel tower and the museums as well as walking a lot around beautiful areas   however pick-pockets got my phone on a busy metro trip despite my vigilance beware and do not leave anything in your pockets that has value another unpleasant thing is the prevalence of smoking everywhere in addition of being a deadly habit that has nothing cool about it it is always in your face and you cannot sit at a terrace without being smoked out
30  1462739962
so i have been to paris more than 40 times and it remains one of my favourite cities in the world i had family living there for a few years and visited nearly every second weekend   there is so much to do and see there i always discover something new every visit  many people say that parisians are not friendly and this is not true  follow these bits of advice for a better experience      1 learn at least a few pleasantries in french  it goes a long way and is likely a big source of humour for parisians :)    2 close your menu!  in french culture it is rude for the waiters to come to you table (and they are usually busy) until you have indicated that you are ready to order by closing your menu  also things come the way they are described  don&#39;t expect the kitchen to make alterations      3 ask for the &quot;l&#39;addition&quot; when you are ready to pay most places will not bring it until you ask they will not try and rush you out like in many other places  a polite wave will do  don&#39;t shout for service     4 when you enter a small shop make eye contact with the first staff you see and greet them  this is polite and they will not shadow you around as you might expect in other countries      5 treat people with respect and you will get it back  this applies everywhere but especially in paris
29  1462740182
expensive lots of smokers not what one thinks of as typically french
14  1462740351
paris is not as romantic as you thought the security is bad
47  1462741379
we were in paris two years ago and did a whirlwind sightseeing tour of  the major attractions so this time around we had a more relaxed time exploring the city streets and finding the occasional eatery paris is an expensive city to visit and the locals favour those who speak french even just a little it was quite warm weather last week and i found it sad that when you sit down and order a meal in a cafe&#47;restaurant you were not offered a glass of water seniors struggle with the heat and a glass of water would be a pleasant gesture also the lack of public conveniences is quite distressing especially for seniors food is very expensive compared to australia so we ate out at cafes rather than restaurants and probably only three times in the week the other nights we would buy some salad cold meat or pate fruit and a bread stick from the local supermarket and have &quot;a picnic on the bed in our hotel&quot; the quality of the local produce is good and very reasonably priced
8   1462742445
loved the atmosphere and variety of art galleries     did not like the amount of rubbish on the streets but similar to many capital cities
7   1462743107
yes it easy get around in paris
19  1462743853
eiffel tower   monets garden   parks and scenery along the seine river   arc de triumphe
72  1462748473
great culture great food excellent metro system - so easy to get around and very helpful parisians
31  1462749807
using the metro was easy and gets you everywhere
45  1462753670
great place very nice helpful people nice clean city with great transport even though you can walk to lot many places once in central paris this place is very good for a family vacation will come back again!!!
61  1462755777
a beautiful city that i could have spent quite a few more days exploring will be back next year for sure!
64  1462761340
we were told that paris was quiet at the time we went - which it seemed to be but it didn&#39;t deter us  it was easy to get around and we found that hospitality and service was pretty good overall still a lot of people at the usual tourist locations  wished our australian dollar went a little further against the euro  nevertheless we loved every minute of our short stay in paris musee d&#39;orsay was a treat
28  1462770825
fantastic city and easy to get around once you understand the metro
88  1462771095
our stay at the normandy made it very easy to see a lot as we only had 5 days
71  1462771220
there was plenty to do we walked and walked for hours every day got some great photos too
10  1462773594
short stay in transit on bike touring trip just time for snack in friday market  a tour of notre dame (great canadian guide) and terrific bistro late lunch&#47;dinner sunny and warm was a bonus bike paths continue to improve
55  1462774685
was in for a short stay so couldnt spend more time on things but worth a visit
27  1462776189
wear comfy footwear as your feet will ache from all the sightseeing  a great city break
25  1462777487
what to say about paris? it&#39;s big dirty smelly seedy sleazy  on the down side it&#39;s posh overpriced expensive and full of itself so what&#39;s not to like? the thing that always strikes me about paris is that people live in most of it so even the most ordinary streets have proper shops with bakeries greengrocers and mini-supermarkets  - and check out &quot;market streets&quot; like rue mouffetard and rue levis and food markets (too numeous to list)  where to eat? anywhere where you like the menu and the prices cafes can be really good value we&#39;ve been going to paris since the 1980s and can probably count on our fingers the number of average meals we&#39;ve had  getting around is easy  - we use the metro for speed and feet for when we&#39;ve no place particular to go and loads of time to get there  once you&#39;ve been a few times and done all the &quot;big stuff&quot; (louvre notre dame sacre coeur eiffel tower champs elysees etc etc etc) check out the less well-kown places - musees rodin marmottan guimet bourdelle to name but a few) and just amble around the back streets and take in the surprising &quot;villagey-ness&quot; of this amazing capital city
13  1462778084
we had a 3 day l&#39;open top tour bus and river cruise ticket which was excellent value and a great way to see the sights and experience paris for the first time there is so much to do and see sacre coure is well worth a visit latin quarter is good for eating on the hoof if you don&#39;t want to spend time sat in a restaurant or bar waiting for food the only thing negative is lack of public toilets and bicycle lanes cyclists everywhere and crossing was difficult only because i have limited mobility
82  1462778647
plenty to see and do metro is excellent recommend the river trip from the eiffel tower and the open top bus tours everyone should visit paris every few years it&#39;s a great place to go
20  1462785213
paris is the most beautuful city in the world  each time i visit i explore new stuff  just love the parisian way of life -
5   1462785445
this time we have faced a lot of policemen and even army on the streets there were everywhere and these was not giving the feeling of security but viseversa
49  1462788102
i was looking for the identity of paris unfortunately i did not find it
38  1462788498
Ã—Â Ã—â€”Ã—Å¾Ã—â€œ Ã—Â¨Ã—â€¢Ã—Å¾Ã—Â Ã—ËœÃ—â„¢ Ã—Â Ã—â€¢Ã—â€” Ã—Å¾Ã—ÂÃ—â€¢Ã—â€œ Ã—Å“Ã—ËœÃ—â„¢Ã—â„¢Ã—Å“ Ã—â€˜Ã—ÂÃ—Å¾Ã—Â¦Ã—Â¢Ã—â€¢Ã—Âª Ã—â€Ã—Å¾Ã—ËœÃ—Â¨Ã—â€¢ Ã—Å“Ã—Â¦Ã—Â¢Ã—Â¨Ã—â„¢ Ã—â€™Ã—Â Ã—ÂÃ—â€“Ã—â€ºÃ—â€¢Ã—Â¨ Ã—Å“Ã—Â¨Ã—Â¢Ã—â€ Ã—ÂÃ—Âª Ã—â€Ã—â€Ã—Å¾Ã—ÂªÃ—Â Ã—â€ Ã—â€Ã—Å¾Ã—Â¢Ã—Â¦Ã—â€˜Ã—Â Ã—Âª   Ã—â€¢Ã—â€Ã—ÂÃ—â„¢Ã—Â Ã—Â¡Ã—â€¢Ã—Â¤Ã—â„¢Ã—Âª Ã—â€˜Ã—Å¾Ã—â€™Ã—â€œÃ—Å“ Ã—ÂÃ—â„¢Ã—â„¢Ã—Â¤Ã—Å“ (Ã—â„¢Ã—â€¢Ã—ÂªÃ—Â¨ Ã—Å¾Ã—Â©Ã—Â¢Ã—â€ Ã—â€¢Ã—â€”Ã—Â¦Ã—â„¢!!) Ã—â€¢Ã—â€Ã—Â¢Ã—â€¢Ã—â€˜Ã—â€œÃ—â€ Ã—Â©Ã—â„¢Ã—â€ºÃ—Å“Ã—Â Ã—â€¢ Ã—Å“Ã—â€”Ã—Â¡Ã—â€¢Ã—Å¡ 75 Ã—â„¢Ã—â€¢Ã—Â¨Ã—â€¢ Ã—Å“Ã—â€“Ã—â€¢Ã—â€™ Ã—ÂÃ—Â Ã—â€Ã—â„¢Ã—â„¢Ã—Â Ã—â€¢ Ã—Å¾Ã—â€“Ã—Å¾Ã—â„¢Ã—Â Ã—â„¢Ã—Â Ã—â„¢Ã—â€¢Ã—Å¾Ã—â„¢Ã—â„¢Ã—Â Ã—Å¾Ã—Â¨Ã—ÂÃ—Â© Ã—ÂÃ—Âª Ã—â€Ã—â€ºÃ—Â¨Ã—ËœÃ—â„¢Ã—Â¡Ã—â„¢Ã—Â Ã—Å“Ã—â„¢Ã—â€¢Ã—Â¨Ã—â€¢-Ã—â€œÃ—â„¢Ã—Â¡Ã—Â Ã—â„¢ Ã—â€¢Ã—Å“Ã—Â Ã—Â§Ã—â€¢Ã—Â Ã—â„¢Ã—Â Ã—â€˜Ã—Å¾Ã—Â§Ã—â€¢Ã—Â Ã—â€˜Ã—Â§Ã—â€¢Ã—Â¤Ã—â€¢Ã—Âª Ã—ÂÃ—â€˜Ã—Å“ Ã—Â¡Ã—Å¡ Ã—â€Ã—â€ºÃ—Å“ Ã—Å¾Ã—ÂÃ—â€¢Ã—â€œ Ã—Â Ã—â€”Ã—Å¾Ã—â€œ Ã—â€¢Ã—Å¾Ã—â€¢Ã—Å¾Ã—Å“Ã—Â¥
23  1462789291
it&#39;s my second trip and this trip is focused to the modern architecture in la defence
35  1462790800
first time in paris and it exceeded all my expectations if only i could afford an apartment on the left bank then i&#39;d be in heaven
84  1462792445
this is town where i would like to live :-)
10  1462797556
love love love it it is my favorite city to visit
67  1462798226
paris is a beautiful city! full of wonderful places to visit both day and night even the same places one should visit in both rounds great food only downside is for those traveling by car: it&#39;s almost impossible to get parking spot worth doing the walking tour!
32  1462799447
&quot;i love paris in the summer i love paris in the fall!&quot; any day any time of the year it is always busy but that is whyeverybody goes to paris take the hop on bus get to know the city and then walk
9   1462799467
paris is amazing place with great connectivity the infrastructure is amazing but seriously disappointed to see no public toiletsif you are staying in good places you are absolutely safe its a true place for art loversfrench tourism really helped a lot
61  1462801088
bring some walking shoes and a street map and then simply wander throughout paris for days you can see great sites without even visiting the major attractions!it is hard to find a bad meal in paris this city remains one of the most beautiful in the world
8   1462801424
a great city lovely people and accessible good transport links from airports although taxis are advisable for a first time visit allow time to get around as foot traffic can be dense but walk whenever possible a lot of paris&#39; best views are in between monuments not at them!    would strongly recommend river cruises and bus tours by the time you have deciphered the metro the bus or boat have got you there!    we ate in carmine on rue suffren a 10 minute walk from the eiffel tower on the junction with the ecole militaire absolutely great staff lovely cocktails and food!
70  1462804344
paris is great in every possible way and recommend it to all to see it   i have nothing negative to say about such a beautiful city Ã¢ËœÂºÃ¢ËœÂºÃ¢ËœÂº
47  1462805150
beautiful!!!! so many amassing places to see!!!
51  1462805254
one of the most beautiful cities in the world! can easily live there a must see food pretty expensive based on sa rand but can get good value at supermarkets fantastic bus and rail system that makes it very easy and quick to get around to anywhere be very careful where you pick accommodation rather pay a little extra and stay at least 4 star
97  1462807584
expensive and queues are endless
64  1462810914
we ate in local restaurants in montmartre; there were dozens of places to choose from we can&#39;t pick out favourite places as we have visited paris many times before over half a century and know it well the whole city is beautiful the mÃƒÂ©tro is cheap and easy to use and the attractions numberless - museumsthe river the eiffel tower shops the different areas of the city parks and interesting streets to stroll along we weren&#39;t looking for things to do specifically for the mature as we&#39;re still fit enough to walk fair distances and climb steps if you want to see how paris looked before haussmann created the wide boulevards familiar to us today go to the marais district - the 16th century buildings in the place des vosges are unchanged from when the area was constructed and the surrounding streets date from the same period
20  1462817917
clean quiet and lots of attractions it is partially because it was rainy days
14  1462819820
the city is charming walk around and savour the extravagant beauty of paris let yourself embraced by the mix of colours flavours the special light that flows on the old buildings take a moment to savour a noisette on a terrace and look at locals and people passing-by visit paris together with the people you love and just live the moment
69  1462822820
very easy to travel by bus instead of metro this way you get to see some fabulous streets and buildings
31  1462826668
the sight seeing and the shops were great the hop on hop off busses would have been better if they went in both directions
7   1462826845
fantastic food great shopping interesting museums historical sights and variety of markets wonderful flea markets visit place algeri a favourite  place of bric a brac and vintage clothes
60  1462826846
great people  great place
33  1462827049
beautiful city!
23  1462829727
charming town with extraordinary museums places restaurants and theaters  walk on the streets neighborhoods along the seine are unforgettable experiences
13  1462833150
paris is a beautiful city with a lot to see
73  1462833262
beautiful city
32  1462835146
i love paris it is easy to get around by train bus or taxi (uber)   who`s in the marais is great for food and cocktails   silencio on rue montmarte is another great spot to hangout with the trendy fashionable set in paris but you have to know somebody or be super cool to get in as it`s a members club because most things finish at 2am this is a great spot for a super late night  the marais also has many options of restaurants and cafe`s and night life    there are many exhibitions to see currently there is the seydou keita photography exhibition at the grand palais which is simple breathtaking the exhibition takes you through all his works over the decades and his unique technique and style in photography
56  1462850316
city to dirty and too many beggars around
63  1462853767
loved paris for its beautiful buildings history passionate people and food loved the louvre and eiffel tower fairly easy to get around paris rickshaws are fun too many street vendors however trying to sell tourist stuff consistently in your face around the main tourist spots rather annoying
76  1462856209
musÃƒÂ©e marmottan monet was a wonderful collection of monet manet sisley pissaro etc enjoyed the special exhibit of children in art (included renoir and picasso) and the room dedicated to berthe morisot married into the manet family good blend of art and history particularly exciting for me to see &quot;impression&quot; of l&#39;havre and &quot;le pont de l&#39;europe gare saint-lazare&quot; big room of his later water-lilies series when he was suffering cataracts later in life enjoyed the picasso quote- paraphrasing: &quot;when i was a child i painted like rubens but it has taken the rest of my life to learn how to paint like them (children)&quot;
15  1462861310
once you know your way around its an easy city but you don&#39;t get much help with directions from the locals  nice to have somewhere to have dinner before 7pm i found somewhere nearby
12  1462861751
it&#39;s the most beautiful city of the world it is here to explore and enjoy be tolerant for poor services due to the french workers attitudes
80  1462862680
great city with impeccable history and beauty
39  1462863347
the location of apartment is ok apartment needs a handyman and general cleaning
76  1462863681
good range of things to see and do found the locals very rude
54  1462864282
amazing and wonderful paris - i will comeback
86  1462864705
paris is always a place to unwind with beautiful places to visit and great experiences to unfold
31  1462865254
just go it&#39;s a pleasant city and amazing sights
45  1462865345
if you go to paris you must see the catacombs - i have been twice now and loved them each time - they are historical and are to be respected and shouldn&#39;t be treated as  just a goulish tourist attraction  for me they are very emotional and moving and i would recommend them every time and i always do  the other obvious attractions are all worth going to and of course a must see
3   1462865726
we enjoyed our trip up the eiffel tower our visit to montmartrethe beautiful parkswe enjoy walking and also used the metro a great deal the weather in april wasn&#39;t greatcolder than we anticipatedwe didn&#39;t eat out alot but would pack a delicious picnic lunch each day
7   1462869155
super galleries museums and gardens  notre dame impressive but if you want to see beautiful cathedrals stay in england or go to italy
20  1462871512
visits museum d&#39;orsay louvre cathedrale notre dame de paris
21  1462872591
beautifull!!!!
38  1462872846
paris is a magical city
55  1460065958
the usual historical sites are always worth return visits but we also enjoyed the marchÃƒÂ© aux puces on monday (huge flea market) and the swing jazz in the caveau de la huchette bar near notre dame!
57  1460066558
disneyland paris
78  1460073797
the metro made sightseeing easy however the city is very expensive especially the restaurants  expect security to be present in all places and at all times
69  1460095976
velib bikes - perhaps use the app as we had problems finding spaces to return the bikes  montparnasse tower at dusk  rue damisunil  mozze n co for lunch near invalids bank of the seine  browsing in bon marche
38  1460098067
there is always a reason to come back to pariswe love it!  this time it was the pantheon and the church near it  the shops and shop keepers the friendliness of the parisiansthe joie de vivre!
66  1460099898
musee rodin was a delight on a sunny day notre dame surprised us because it was free yet interestingly detailed and well organised
62  1460101676
restaurants cafes excellent  recommend le metro bar rue de st germain
72  1460104151
we were not actually visiting paris but breaking a journey from ipswich to barcelona by train
84  1460104744
boat trip on seine
52  1460104759
we chose a stay near eiffel tower do that we can go there relax &amp; have our coffee at the park   the metro link to all tourist location just get a day pass you can travel paris within a day in a relax mode metro is not user friendly for wchr pac &amp; travelling with children with pram just has to be careful of your wallet at all times be careful when you see groups of people or even children
91  1460105209
so much lovely art to see beautiful shops and wonderful ambience
55  1460110871
we enjoyed eating in the marais district some great restaurants
19  1460111082
moulin rouge!! if you go to paris and don&#39;t visit it you didn&#39;t go to paris at all!     it is extremely easy to get around in paris i loved it!     you do gotta watch out for pocket-pickers really annoying
85  1460111170
the louvre and napoleon&#39;s apartments le train bleu natural history museum a river trip and versailles are just some must-dos in paris however paris has a huge immigration issue and as a result many pickpockets and beggars have sprung up over the last years due to lack of work for newcomers be very aware in the north of the city and also around tourist attractions for conmen stopping you to ask anything at all and also of having all bags secured especially at crossings and lines gypsy gangs have complicated ruses to get you parting with cash you will also find many outdoors tables will be accosted by beggars during meals be aware also that not so many people speak english here and many don&#39;t want to have enough french to get by paris is still beautiful but it is suffering and my tip is to visit off season and use taxis to get around more run down areas such as gare du nord and magenta
1   1460120494
it was good it is busy everywhere
66  1460120825
amazing architecture and great cafe culture easy to get around on the metro  be warned  eating and drinking is very expensive !
29  1460123071
the people were actually really friendly and very helpful didn&#39;t seem to mind the fact our french was pretty hopeless watch out for pickpockets on the metro though - my sister nearly got caught out on an escalator
21  1460124716
i go to paris for the marathon in april just stay for two nights so no time or energy to travel around i should have stay much longer to get around to see the city   i like the tour eiffel and the river by the side and the square louise michel is a good place to see the panoramic of the city   if you like pastries or dessert macaroons ispahan you&#39;ll love it! especially &quot;laduree&quot;!!!  it&#39;s easy to get around paris by metro!
54  1460125298
a really lovely city  everyone very friendly  moulin rouge show was amazing - we all loved it  yes it is expensive but great  wish we had used batobus properly to go all the way round the sights - we walked on the first day got batobus tickets on day 2 and this was a nice way to see paris  eiffel tower - must admit not really worth it but has to be done  we got there before 10 and queues only about 45 mins to second floor  staff all really friendly here too  louvre - unless you really like &#47; understand art really not worth the 15 hour wait we had in the rain outside  pyramid disappointing  staff not really friendly in general  however the actual louvre buildings are amazing  champs elysees great and arc de triomphe - though great shopping in other areas too  wish we had longer in the latin quarter - some great restaurants etc houses &#47; buildings even more beautiful than the rest of paris  when we go back think we would stay in the area  underground rammed even in none-busy times - be aware if you plan to travel on it with suitcases &#47; children - people really push their way in no matter what - worse than london!!  loved sitting outside the brasseries watching the world go by while having a beer
63  1460138012
street cafes excellent and all the must do things easy to get to moulin rouge fabulous
22  1460139023
amazing place truly magical
4   1460142032
we enjoyed visiting the monuments asthey were beautiful and had restaurants near it we also got there without any difficulty however it was difficult to tell where we were on the subway maps
93  1460142207
loads of history and things to see prices are very high though charging over 8Ã¢â€šÂ¬ for a draught beer at a cafe is overkill
88  1460146570
you can&#39;t beat paris for atmosphere you can get around so easily and for one i think the parisians are great so much to do and see    be careful of pickpockets and scam artists - they&#39;re everywhere!
86  1460146883
very expensive city you never want to pay 10Ã¢â€šÂ¬ for 1 beer 1664 wich cost only 1Ã¢â€šÂ¬ for 4 pizza and 7 beers in have paid 230Ã¢â€šÂ¬ you are insane people of paris?
67  1460149339
great city so much to see and do will it cost you without a doubt so get saving but so worth itmoulin rouge is a must see the louve is great but give your self enough time its huge if you love and appreciate great buildings art and just chilling and enjoying a city break then paris is a must ps if you love running then you will enjoy it even more
59  1460149623
booking lifts for eiffel tower saved waiting in queues and combined with evening cruise along seine was amazing
17  1460154210
great place
64  1460154521
fantastic museums art and architecture  easy to navigate the city using buses and the metro
47  1460179002
we have been to paris many times before and love it this trip was a precursor to an organised tour which starts today paris is an amazing city steeped in history amazing culture and by and large a very friendly city
12  1460183106
lovely and romantic city!
81  1460187603
paris is a fascinating city with so much to offer families and couples  we originally thought 2 weeks might be a little long in one city but there is so much to do and see we  did not get time to see everything  it is really a walking city but the metro is so convenient that we used it every day    there is a museum pass which may be cheaper for those expecting to see many museums but it does not cover the eiffel tower  the tower is not expensive to visit and even cheaper if you choose to walk up the steps( more fun with older children also)&#47; the ride down is free    just go!
94  1460188046
the city of light! always wonderful to visit paris!
33  1460188281
the eiffel tower tour and lunch was incredible 3 courses each with beer or wine and access to second floor of eiffel tower for Ã‚Â£30 each great value for money    also the river cruise is well worth doing as you get to see a lot of the sights that way
3   1460188657
the moulin rouge show was very good but the staff were a bit indifferent the louvre was amazing and to be recommended but you need 2 or more days to see it all  we visited the eiffel tower after dark and that was brilliant to see paris at night from the top  visiting notre dame is also a must we were there during a communion which added to the interest of this fabulous cathedral  travelling around paris was very easy using the metro and rer trains
1   1460194815
people dont speak english! dirty city with arrogant snob!!!otherwise food is good Ã°Å¸ËœÅ
65  1460195324
opera and madeline  french cuisine and sushi
40  1460196323
what an amazing city with tons to do the carne (book of ten single tickets) for the metro is a must the fact that you can book  &quot;no queue&quot; tickets on the internet (i used tiqetcom) is incredible - it works as the queues can get very long   the monuments and museums are well organised (if not very expensive for my sad rand) the orsay is just too good for wordssmaller museums like musee de poupee are worth the visit even just walking around is great lots of walking to be done around mont martre&#47;champs elyssee&#47;tuileries  there was more litter than we expected beggars are more abundant given the current political upheavals but they&#39;re not too in your face   eating out is very expensive so we stuck to casual pub type vibes
97  1460196325
paris are beautiful it worth every penny spent i will be back
87  1460196459
the historical heritage of paris is definitely a must-see  however you have to be alert of the people around  robbery and pick-pocket are common  in addition the customer services haven&#39;t been improved over the past 10 or 20 years
5   1460197787
far too many beggars and so called refugees everywhere
13  1460198889
amazing city!  but be ready to spend some money  it&#39;s not a cheap city  beware of pickpockets in the main touristic places
23  1460199122
so much homeless people! how could that be in parijs? omg well the ladefense area looks good but boring not to mention it is also clean there; untill u step onto the other side you will be hit with the stench of p**s homeless people beggars and pickpocket thanks guys for stealing my walletfrom holland with love
14  1460207188
paris is beautiful i recommend walking to places rather than getting the metro because everything is so close! also go with a best friend or a partner as you will have so much more fun if you&#39;re a student you can get to see so many things for free and i would strongly recommend the pompadou !
96  1460207246
big city you would need a week to see all very expensive even compared to london
83  1460207767
paris is great fun diverse - and can be inexpensive if you research well     it&#39;s also compact and you can get to most areas on foot easily - but the metro is cheap and efficient (if there are no strikes) - it is not disabled friendly - or child buggy friendly however
38  1460208171
favorite places would be the eiffel tower champs elysee ate at pizza pino champs elysee most of the time it is so easy to get around by metro shopping eating and visiting museums are mostly what we did
30  1460208423
paris in the spring time and champions league quarter final!great sites good weather good cheap tourist menus in latin quarter
98  1460208852
if using easy bus to and from airport please leave plenty of time as you can wait an hour or over and miss your flight
63  1460209050
very very expensive everything
28  1460209398
au tour midi et minuit jazz venue and restaurant hippopotamus
68  1460209469
we had a great time in disneyland and the eiffel tower
37  1460210060
people quite pushy buildings beautiful
30  1460210791
beautiful city and friendly people staff in shops restaurants and other service establishments more than willing to help a tourist&#47;visitor had evening in paris before moving onto spain just walk down any street and you will find somewhere great to eat
19  1460211339
it&#39;s very good
17  1460211896
walking (a lot) in the streets and poch avenues   museuns: piccaso - booked in advance to avoid the que (a sculptures exihibition right now); fondation louis vuitons ( the building itself is worth seeing and the on going exihibition of chinise artists); marmoten- manet (an incredible collection of manet works of berthe morrisot (was married to manet&#39;s brother) and the on going exihibition of child and art   les fables de fontaine michlin 1* restaurant (booked in advance via internate)  3 concerts of classical music in 3 different churches (st ephrem la madalaine l&#39;isle) booked in advance  there are free public toilets in the streets (not enough) cleaned automatically - that is also a culture!
40  1460211971
paris caffe&#39;s  historical buildings
1   1460212545
travel is very inexpensive as compared to london lots of historic sites to visit restaurants serve excellent food &amp; you can shop for good food wine gateaux etc at several supermarkets people are friendly &amp; helpful &amp; you feel welcomed
81  1460212612
beautiful city for wandering lots to see day and night  fantastic view from arc de trimphe  pompidou centre (modern art) amazing  really easy to get around on metro (buy a &#39;carnet&#39; pack of 10 tickets to save money ) or by cab
32  1460212730
lot of pick pocket hard to find police and people in paris not really care of tourist also the train!becarefull of your bags and wallet
23  1460214869
overall the trip was extremely enjoyable location of hotel was excellent and we were able to visit all the sites of paris we had planned to
74  1460218066
crossing the roads needs care book trips before travelling eiffle tower amazing eating out very expensive
7   1460218744
beautiful tourist attractions
33  1460219201
the location is great you step out of the door and the seine is right across the road with notre dame to your right  the staff were so helpful and their knowledge and advice ensured we got the most from our trip  they helped with travel telling us about the bateaux bus and sights to see as well as great restaurants literally round the corner in st germain  we had a superb breakfast at cafe de paris and then had dinner there including snails &amp; oysters  when we came out a band was playing jazz in the street which created such an atmosphere so parisian!!     the worst thing the loo door was hard to shut and kept swinging open when least expected!
24  1460219830
people from paris don&#39;t speak english and if you are british is big no for them
76  1460220634
this is my first visit to paris it was great but less than expectation
16  1460222106
paris is a fantastic city would recommend 100% to visit
93  1460222301
firstly i found the parisian people very nice and eager to help whenever we were lost it was much busier than i could have imagined for the month of april nevertheless we saw everything we wanted to see we had been recommended a wonderful restaurant les papilles a memorable evening we also discovered that if you reserve a table through the internet you receive a discount whilst we walked everywhere we did take the metro very easy to follow and a must to go long distances particularly the marche au puces this is a must for mature couples displaying great memorabilia  despite the recent tragic events we felt very safe there was a presence of soldiers and gendermerie but not intrusive    enjoy!!!
60  1460222784
a lovely city with so much to see travel around is very easy and cheap using the metro so many good places to eat it&#39;s hard to choose the weather was so good in april we didn&#39;t want to miss the sunshine by being inside will definitely go back to visit the museums and galleries
30  1460223163
the main tourist spots are a must but the amount of people bugging u 4 money is annoying! the metro is easy to use to get around but didn&#39;t want to miss the sites so was so tired from walking around all day
79  1460223762
beautiful and sophisticated french women liking my outfit and english gentlemen&#39;s classic style!got few numbers as a matter facti did not even try harder!
65  1460224721
fabulous city for all ages beautiful buildings dipped in history great parks for tranquility great food
34  1460227378
beautiful citybuildings and history but ruined by the homeless eastern europeans constantly begging and pickpocketswe had our phone stolen by one at the eiffel tower
28  1460227438
well what can you say? it&#39;s paris and it&#39;s great! so much to see and do generally nice people obviouysly very expensive in the city centre less so if you are slightly out of the centre
4   1460232298
very very expensive city but most capitals are     lovely people though and very helpful     stay away from gare du nord station at night time
90  1460235266
great lively city
37  1460243825
use the batobus it&#39;s well worth the cost! must try the berthillon ice cream learn some of the language before you go as the locals appreciate you trying!
62  1460249309
paris is beautifulthe whole city is one large work of art and it can not be captured in a single photo the corner cafes the people riding their bikes the architecture of the buildings and the streets all of it  is beautiful and this is before you go see the eiffel tower
9   1460249658
lots to see and do research is the key lots of places accessible on foot with lovely views on the way plan plan plan doesn&#39;t have to cost a fortune if you plan it right
67  1460251521
this hotel is in a lovely area with quick access to rail station restaurants  medical clinic  pharmacies and a beautiful length of delightful gated garden just around the corner
19  1460254396
we stayed for a week for the marathon and the sights   the hop on and off tour bus over two days was easy on the feet and made the sight seeing most enjoyable in a relaxed and less hurried fashion the architecture is wonderful napoleons tomb was mind blowing  the cemetery was beautiful and the tombs works of art i visited oscar wilde edith piaf fredric chopin modigliani jim morrison and others a quite and peaceful few hours away from the paris theatre of life
87  1460263854
as a frequent visitor to paris to visit family it is a great place to explore and indulge in its history  many brilliant musee shopping and enjoy being there walking is the best way to see and get to know any city that all means the exercise allows you to  indulge in all the glorious patisserie and cuisine  get a good map and walk
2   1460271165
loved all the major sites and the museums but very disappointed that we could not read what each exhibit was about the displays were only in french in every other country we have been to where english is not the main language the museums have the display cards in both their language and in english so not really able to enjoy the exhibits as much as we thought we would we could hear other visitors voicing the same opinion we did try audio guides and guided tours they do help but there is nothing more enjoyable that just wandering and viewing by yourselves so dont go to paris museums or sites expecting to be able to understand what you are looking at unless your can read french
41  1460272149
palace of versaille notre dame eiffel tower louvre  + many more sights
61  1460273162
i have stayed in paris many times experiencing  paris is something everyone should do the culture the people and its social scene make it one of the world&#39;s top cities on your bucket list
96  1460275159
do the night bike tour of paris with fat tires it includes the river boat tour too we did it at the start of the stay it gave us a lot of useful tips and ideas of places to go and see on our own in the rest of the holiday the metro 1 3 or 5 day travel cards were great too and you can also use them on the busses and trains
64  1460275729
go
55  1460277965
paris is romantic and amaizing for those who love paris but it was much better a few years ago from touristic point of view it is not safe now especially  in the late evening too many annoying &quot;street-sellers&quot; too many thiefs and persons with criminal intentions
80  1460278365
beautiful city and a perfect romantic stay     quite expensive stay away from &#39;touristy&#39; cafes by the eiffel tour etc they will sting you on price!     highlights and must dos   bateaux parisien seiene river cruise we did ours at lunch time and it was amazing views food weather and overall experience!     if you can afford it le jules verne slightly over priced but an experience unlike any other in paris     walk as much and as far as you can you will see and experience so much more and save your self a lot in cab fares
78  1460279973
had a guide who was fab definitely worth doing she knew all the best places the bikes are great fun too
36  1460281148
disney was simply appalling booked a 4 star which in reality was a 2 at best and left early to the relative civilisation of central paris paris is what it is overpriced but lovely!
64  1460281498
paris is a very nice city you can spend good time here visiting great tourist spots  eiffel tower   muse de orsay  louvre  arc de triomphe
3   1460281671
night shot of eiffel tour cannot be missed the first sunday of each month you can enjoy free entry to most of the museums but remember to arrive the place early to avoid long queue
11  1460281697
very easy to travel in paris metro station is always near big choice of eateries easy to see low life and high life!!
80  1460282211
beautiful sights bustling city great city to visit!
1   1460282490
the hop on&#47;hop off bus was a great way to get to know and explore the city
95  1460282796
unless you speak french i wouldn&#39;t recommend some of the people were downright rude when trying to ask a question with very limited french
26  1460282886
better go via public transport not a good idea to drive in paris
21  1460283357
be careful with your stuff  my phone was stolen!!
76  1460284388
definitely go up the eiffel tower in the evening very atmospheric if you have children who like that sort of thing then also definitely visit eurodisney as it is only 40 minutes away on the train and very easy to get to musÃƒÂ©e carnavalet is also a really nice intimate (and free) museum in le marais that children will enjoy also lots for children in parque les tuilleries (trampolines playground merry go round)
14  1460285153
the &#39;open tour&#39; bus was a great way to see all the sights if you have limited time in the city
14  1460291651
the best for solo trsvellers are museums all are very easy in the paris
19  1460292120
children and under 25s free in museums is fantastic for a family trip like ours staff in museums were friendly and welcoming despite queues   loved the eiffel tower it was a beautiful thing but not sure it was worth queuing to get to the top floor  easy to find places to eat st michelle particularly good value for eating  metro very straightforward but we enjoyed walking
42  1460293021
three day metro pass was a great buy  only two days spent there do more of a reconnisence for future trips  tower tour at notre dame was well worth the wait
36  1460293403
people watching
77  1460293634
beautiful city great shopping!
5   1460294550
so easy to navigate around paris due to the amount of public transport and easy metro links visited most of the landmarks of paris has to be said that the eiffel tower is beautiful on a night time would definitely rate this trip and recommend that people go
2   1460295050
very easy to get around the two day pass on the batobus was brilliant  the parisians actually seemed very friendly!
67  1460295903
i love paris and this is my 4th visit brought my daughter to celebrate her 21st birthday  favourite places to visit are: the eiffel tower (go up at night to get a view of the city lit up);  the garnier opera house (haven&#39;t seen an opera or a ballet there yet i just love the building) followed by shopping at galerie lafayette; walk through montmartre up to the sacre couer to see the amazing views of paris and the beautiful cathedral; experience a caberet show either the moulin rouge or the the lido (expensive but a really nice treat buy a &#39;champagne ticket&#39; not the meal package as that can be very pricey)  getting round paris is really easy but take comfortable shoes! we walked to lots of the main attractions as the weather was kind and i just love walking along the seine the metro is relatively easy to navigate if you&#39;re used to undergrounds the batobus is well worth using as well boats run every 25mins but only go in an anti-clockwise direction so plan your route beforehand (there are 8 stops for you to get on and off) plenty of cafes and restaurants all over paris - you are spoilt for choice and if you choose the &#39;plat du jour&#39; it&#39;s great value for money
20  1460296083
food was easy too get but the underground train system us bug but complicated for new tourists
15  1460296468
i love paris so difficult to think of negatives it seemed less busy but still vibrant revisited sacre coeur notre dame arc de triumph latin quarter montmartre and musee d&#39;orsay with companion new to paris and loved it all however some of the best bits are just sitting outside cafes in the sunshine people watching!
53  1460296691
keep your eye out for the scammers
62  1460296706
holybelly makes the best breakfast though there&#39;s usually a queue it&#39;s totally worth it
83  1460297993
i was here specifically for the champions league game city played psg great experience also visited the louvre &amp; the touilleries lovely stay and its really easy to get around on the metro i liked saint germain de pres although it is an expensive part of paris i would stay here again
91  1460298650
mai do - excellent vietnamese restaurant  le bon marche   le hammam pacha
45  1460299110
we all had a great time walking around the city taking in all the sites and visiting some of its monuments we enjoyed all the different cafes and restaurants especially the pastries we couldn&#39;t get enough i do recommend booking some of the site seeing in advance like going up the eiffel tower and visiting the louvre as the queues can get a bit long disney was a lot of fun too
20  1460299169
we went to the eiffel tower arc de triumph sacre cÃ…â€œur the catacombs moulin rouge all were fabulous however watch for pick pockets! my daughter had her phone stolen! people wanting your signature for deaf people then stealing from you!!!! devastated
13  1460299196
metro is amazingly easy to use and getting around is quite simple would recommend getting something like the paris pass before travelling as we did - made it possible to plan and use every minute to see as much as possible
84  1460301045
best places to visit include eiffel tower - go early and avoid queues musee d&#39;orsay and l&#39;orangerie if you like impressionism - free for under 24s and 2 for 1 with eurostar montmartre for shabby chic beauty and views over paris walk to see everything or metro is easy transport if your legs are tiredcafes open all hours wherever you go
32  1460301317
beautiful and clean city
81  1460301695
paris is  very beautiful town
23  1460301814
metro easy to use we found it the best way to get about for wonderful vistas of the city go up montparnasse tower (cheaper than eiffel tower without the queues) means the eiffel tower is in your photos!   lovely creperie just up the road from the hotel - etoile d&#39;or excellent value for money and very tasty  if you go over the first sunday of the month all monuments and museums have free entry
88  1460301823
plenty to see and do great sights sounds food and general buzz!
89  1460301845
eiffel tower was grest value for money  sacre coeur in the evening was beautiful  natural history museum was amazing for children- could spend all day there gardens were beautiful
21  1460301866
for me everything in paris is magic i prefer to go in the streets and  looking around i like to be under the tour aiffel and so
44  1460302083
this was my third visit and in all honesty i wouldn&#39;t have went except for the fact that i was taking two children on holiday and it was all new to them  about a week is long enough to see all of paris that you need to and after that i didn&#39;t feel the need to return  a beautiful city and i&#39;d definitely recommend a visit the metro is reliable and cheap the food is inexpensive if you wander off the beaten track the catacombs and eiffel tower (to the top) are unmissable and if you&#39;re under 26 i&#39;d certainly recommend going to as many museums and galleries as possible as they&#39;re free until 26 at which point they become very expensive
38  1460302143
wonderful city which was full of  nice surprises when one goes off the tourist track!the metro makes travel exceedingly easy even to the more outlying districts the left bank as a whole has a great atmosphere and fab eateries one of our favourite places was the luxembourg gardens avoid the tuilleries gardens as many nuisance pedlars and beggars
94  1460303234
eat at any of the brasseries sit on street and watch life pass you by  notre damefree and photo ok without flash  sacre couerfree but no photos  walk along river day and night  forget driving in paris walk or metro
24  1460303276
highlights of holiday - summit of eiffel tower orsay museum &amp; carousel at sacre couer!
48  1460306987
visit the marais for some great food and great service  found an excellent bistro au fil des saisons highly recommended  excellent metro system
73  1460307743
everything about paris is amazing just not enough time to see it all  metro was easy to use and not expensive
37  1460308575
brillant city
77  1460308695
so many tourist attractions so many great restaurants
3   1460309056
public transport as good as barcelona city is just about as gorgeous too  did berlin barcelona and paris this week - berlin a distant third
23  1460309644
plan your trip and then add 3 more days if you can  there is so much to do in paris and each arrondissement has its own charms  do some research before you go to save time and money by enjoying the museums streets and activities not catered to by the tour-bus crowds     save some room in your suitcase for shopping  bring layers an umbrella and at least 2 comfortable pairs of shoes so you can fully enjoy the exotic treats down every cobblestone street and stand up to the hard marble floors of churches and museums
90  1460310365
very atmospheric lovely just to be here and enjoy the wonderful pastries and food easy to navigate and see all the sights helpful staff and friendly waitresses
72  1460310672
simply one of the most exciting cities in the world !!!
70  1460313204
location of hotel was great and staff was friendly
85  1460314460
paris is my favourite city it is beautiful intimate elegant my favourite places to visit are the musee d&#39;orsay the rodin museum and just sitting in the tuileries gardens and i always choose a hotel near la nation about 2 miles from the centre so that i can walk through the streets early morning and see the local street life as parisians rush to work i always walk everywhere because i like seeing people and the beautiful architecture around me
12  1460314812
too many thieives
84  1460316298
used uber cars during our visit and enjoyed the tour bus too plenty of good places to eat and drink
65  1460317325
great time in paris for the fifth time love this city
97  1460319226
the city is amazing and very beautiful but beware of pick pocketing i lost my camera and ipad in the metro!
71  1460319392
loved every minute of the city
16  1460319877
location was great staff friendly clean apartment though not very spacious a toaster and frying pan would have helped
42  1460319897
we love paris for its people and its culture
68  1460324687
everything is different compared to others cities that i visited infact i want to come back soonmany more things to see and explore that i didn&#39;t have the chance especially going to disneylandi love the eiffel tower when i saw it i could hardly walk because it&#39;s like a dream come truewe had dinner at pedra alta a seafood restaurant it was so goodi&#39;m with our family friend who lives in paris for 15 yrs so she showed me aroundthere is a time that i went to montparnasse tower and to moulin rouge in blance alone it&#39;s fine because i just asked the staff at metro and they just showed me the map and told how to go in there
64  1460324821
i have visited paris on more occasions than i care to remember and it never fails to move me it is a tonic for the spirit the eyes and ears!i would urge anyone who has the opportunity to go
79  1460325969
we went with our two children one being 19 months old lot of places did not accomaate children very much a place for couples       we went to disenyland for day we&#39;d recommend staying close to the park with young children it was difficult travelling on the metro and rer into the park for the day
93  1460327871
it was a very nice city with a lot of character saint michel and surrounding areas are definitely worth a visit louvre was amazing!
93  1460328969
brilliant place with lots to do
86  1460332241
the most i liked in paris were - eiffel tower luvre opera house food versailles hotel where i have stayed (royal saint germain in montparnesse) galery lafaette   as i am non smoker in cafes people smoke everywhere and this is the one thing i did not like
61  1460332396
everything&#47;&#47; entrcÃƒÂ´te +moules&#47;&#47; yes very easy to around paris &#47;&#47; disney &amp; more
96  1460344175
it was incredible hope we coming soon
47  1460353138
fantastic city highly recommend ite
68  1460359514
mont martre - notre dame - quartier latin - museums - theÃƒÂ¢tre - palais galliera    metro to get around    to eat : on top of le printemps store - verre ÃƒÂ  pied ÃƒÂ  la rue mouffetards  - la gare
46  1460363179
great old world charm    unfortunate blight is the scam artists operating with impunity even during the visible heightened security
65  1460364411
absolutely beautiful!
34  1460365526
the old wine warehouses at bercy have ben converted into a pleasant bar&#47;restaurant area
81  1460368693
we drove to paris tried to arrive at a time that wouldn&#39;t be as busy with traffic it was still a little hectic but manageable  we got stuck at a traffic light and got harassed by gypsies(?) they were trying to wash our windows and asking for money   a kid( about 12 )jumped in front of our car and wouldn&#39;t move out of the way  a woman was pounding on our window with a squeegee then an older man came over and bent our windshield wiper  what a nightmare!  seems that the city should try and address the aggressive behavior and run them out of the area!  other than that we had an enjoyable time visiting many of the sites good food good weather people seemed to be enjoying themselves  we did see a few more police and military personnel in a few of the areas but felt safe for the most part
0   1460372414
i was at a conference so not really any time for sightseeing
39  1460373797
at present the atmosphere is very jittery soldiers and gendarmes everywhere it is not pleasant i hope it will change soon for the better   on other note all the tourist attractions are still there to be enjoyed i love to go for a different culinary experience to the rue montorgueil it is worth it
93  1460377341
use the metrobuses especially number 69 and 30
7   1460378185
moving around by the underground train was easy many good eating outlets with many choices in the menu
15  1460378200
walking along the left side of la seine revisiting le quartier latin where i once spent a year of my youth and mostly everything is the same! we also went by metro which is very practical and fast  we mostly ate our dinners in the boulevard du montparnasse which is where we stayed at chez fernand le bullier trattore napole  it was only a 20 minutes ride from orly to our hotel novanox that`s very comfortable too paris is my favourite destination
35  1460381018
we made a visit for two concerts and had a little site seeing day on the saturday paris is a very friendly city and the metro is very easy to use quite reasonable to eat and drink out which was nice
16  1460381361
plenty to see and do - make sure you visit the louvre and sacre coeur!paris is hopeless for disabled travellers definitely would not recommend paris pass was brilliant value giving free entrance to lots of places and unlimited free travel underground fab though far too many stairs!lol
21  1460383403
cidade linda com muitas atraÃƒÂ§ÃƒÂµes
0   1460385525
not been for years was short of time but because its so organised and with friendly people we had a great but brief time as always expensive so glad we had so e beautiful south african wines with us Ã°Å¸ËœÅ
12  1460385559
paris no need for words
14  1460387532
beautiful city food is great &amp; metro is very easy to learn
69  1460390176
paris is a wonderful city
21  1460391842
i know paris having worked there for 12 years in the distant past the major change we noticed was that people in the services are much more friendly towards foreigners  ( i am british my wife is german ) than they used to be  on the other hand the traffic is much worse and it is evidently difficult to clean the narrow streets with cars parked on either side also the paving in these streets is in very bad condition!  it is easy to find good moderately priced restaurantsbut of course it is easier if one speaks reasonable french as we doapleasanta
60  1460399846
love: parks crepes cafes brunch la recyclerie the river banks of seine  hate: metro prices
60  1460401065
loved it not my first time there and definitely not my last!
69  1460401609
the tours fashion and food
0   1460410851
stayed on first floor with no view as we were at back of hotel this made it a peaceful room which overlooked a courtyard
74  1460413631
beautiful city
88  1460448674
not enough words in the dictionary to describe the beauty and wonder of this magnificent city!
98  1460453101
french food pastries and shopping
0   1460453515
what is there to say!a cultural capital of the world incredible food wonderful sights and architecture and a friendlier populous than you are led to believe a must for everyone everywhere
50  1460454182
the metro system seems scary but once you have it figured out its really easy to use to get around! and it goes everywhere! we had an unfortunate incident with some refugees but i guess it comes with the territory!
89  1460454302
pastapapÃƒÂ¡ near place vendome is good value for money (pasta or pizza with beer or wine and a dessert for 15-16 euro); nice beer they said was heineken but tastes much better than in the netherlands
90  1460454551
great place - pray for sunshine as it is an outdoor experience
3   1460454902
street sellers everywhere risk of pick pocketing homeless people&#39;s mattresses in the trees even on the boulevard sant germain oppressive security - was even frisked going into mcdonalds however the weather and street cafes were lovely and there were crowds of tourists so the prices are still very high tip - pre book at the picasso museum we were turned away as tickets sold out for next three days
65  1460455304
fantastic city combination of modern and history you can reach all the city by the metro art and historical structure you need to see the symbol of the city eiffel tower is amazing i enjoyed every moment
7   1460455525
awesome
99  1460455747
beauty!!!!!!!!!!!!!    eiffel tower looks awesome in day and beyond world with lights at night  louvere museum is just too good feeling great after selfie with monalisa    metro in city is fabulous!! make sure to get a map when you reach and then just spend 10 minutes to get hang of it very easy to understand every metro station also has enough sing boards
69  1460455940
a melting pot of culture history great cuisine &amp; entertainment  easy to get around with the metro  don&#39;t miss the sainte chapelle for a breath-taking experience  see paris from another view point - go up the dome of the sacrÃƒÂ© coeur or the top of the arc the triomphe or both no long queues and the views are just as good as form the eiffel tower  not a big opera fan? you will be if you have dinner at the bel canto (hÃƒÂ´tel de ville)
25  1460456207
easy access to metro and shopping area
11  1460456336
la pÃƒÂ¢tisserie prÃƒÂ¨s de la place de la rÃƒÂ©publique ÃƒÂ©tait juste ÃƒÂ©normissime !   a l&#39;angle de la rue de l&#39;hÃƒÂ´tel se trouve un restaurant italien oÃƒÂ¹ les pizza sont un pur dÃƒÂ©lice
67  1460458264
the chateau and its garden the french restaurants our favorite chezz papa which is just around the corner 3 mins walk and the pastery shop beside the hotel love the metro very easy to get around and a must buy a week pass for metro and covers all public transport if you going to be in paris for more than a week lourve notre dame  eiffle tower
6   1460458323
beautiful city awesome people but please be extremely careful for the documents etc it gets stolen in a blink of the eye
56  1460458489
paris is like paradise!!!
10  1460458983
great city if you like sightseeing you must visit paris a lots of places to have fun and pleasure excellent food only thing that was worrying me - prices the city itself is not the cheapest one
62  1460459817
effil tower and louvre museum and seine river cruise awesome experience
91  1460461103
an excellent metro although there are a shortage of escalators  that is changing though plenty to do and see the great mosque is a must see it has a lovely courtyard and restaurant  where one can sip mint tea and relax one can tour the mosque the guimet musee of oriental art is also a must see  we ate in chinatown and also ate some very reasonable french food especially at au metro by metro pernety
79  1460461166
sacre-ceour basilica and eiefel must seenboat tour would be amazingcroissant was so delicious and visit laduree for tasty macarons really good metro lines you can easily get wherever you want buy some hot chicken and a bottle of wine and have a picnic near seine river
87  1460463112
great city police should do something about the frauds on the street and pushy salesmen at the beautiful attractions&#47;monuments  they are harassing people and the visitors should be protected from them!
30  1460464483
disneyland actually completes the paris with eiffel tower    nice place but city is not so cleaned theft and roads are not safe as compared to other cities like barcelonaetc
10  1460464506
beautiful when you go to the right areas ;) if you&#39;ve ever lived in london then it just like that like any major large city
85  1460465075
great city
25  1460465698
Ã¥ÂÂ¢Ã¦ÂµÂ®Ã¥Â®Â«Ã¯Â¼Å’Ã¥Å¸Æ’Ã¨ÂÂ²Ã¥Â°â€Ã©â€œÂÃ¥Â¡â€Ã¯Â¼Å’Ã¥Â·Â´Ã©Â»Å½Ã¥Å“Â£Ã¦Â¯ÂÃ©â„¢Â¢Ã¯Â¼Å’Ã¥â€¡Â¯Ã¦â€”â€¹Ã©â€”Â¨Ã©Æ’Â½Ã¥â‚¬Â¼Ã¥Â¾â€”Ã¤Â¸â‚¬Ã§Å“â€¹  Ã¥Â°Â½Ã©â€¡ÂÃ©ÂÂ¿Ã¥Â¼â‚¬Ã¦Â¯â€Ã¨Â¾Æ’Ã¤Â¹Â±Ã§Å¡â€žÃ¥Å’ÂºÃ¯Â¼Å’Ã©Â±Â¼Ã©Â¾â„¢Ã¦Â·Â·Ã¦Ââ€šÃ¯Â¼Å’Ã¦Â³Â¨Ã¦â€žÂÃ¤ÂºÂºÃ§â€Å¸Ã¨Â´Â¢Ã¤ÂºÂ§Ã¥Â®â€°Ã¥â€¦Â¨    Ã¦Ë†â€˜Ã¤Â»Â¬Ã¦ËœÂ¯Ã¥Å“Â¨Ã¥Â¸Æ’Ã©Â²ÂÃ¥Â¡Å¾Ã¥Â°â€Ã¨Â¢Â­Ã¥â€¡Â»Ã¥Ââ€˜Ã§â€Å¸Ã¤Â¸Â¤Ã¥â€˜Â¨Ã¥ÂÅ½Ã¥Å½Â»Ã§Å¡â€žÃ¥Â·Â´Ã©Â»Å½Ã¯Â¼Å’Ã¦â€°â‚¬Ã¤Â»Â¥Ã¥ÂÂªÃ¥Â®â€°Ã¦Å½â€™Ã¤Âºâ€ Ã¤Â¸â‚¬Ã¤Â¸ÂªÃ¥â€˜Â¨Ã¦Å“Â«Ã£â‚¬â€š
65  1463477293
amazing city! one of the best cities in europe :) classy charming rich beautiful!
28  1463477699
if you re searching for english speaking wait stuff and cosy atmosphere you can find it at restaturat called les antiquaires  food was delicious and they serve the most tasty hot chocolate cake (best ever! :))    the only minus the subway line from the cluny hotel to eiffel tower (yellow) was under construction we walked a lot
88  1463477971
we weren&#39;t there long enough really!  having been to paris before however its a lovely place and well worth a visit  kind of one of those cities you have to see especially if you live in europe
21  1463478495
favourite place was louvre    we have lunch and dinner at many different restaurants all over paris all is good     use the metro or taxi very easy to get around      we did not travel with small children
60  1463478642
beautiful
2   1463478691
the city has rich history and hence lots of features to see transport system is quite efficient offering different options depending with one&#39;s preference can never have enough of the city you can always go back
51  1463478870
many places to go and they all lovely metros are well connected so convenient!or walking along the street from one place to another is also a good idea
47  1463478984
louis vuitton foundation was amazing    i loved notre dame tower it was worth queuing more than an hour for it    my trip to pere lachaise cemetery was really nice
85  1463479205
two words: fucking beautiful
46  1463479356
romantic city lovely people great atmosphere
17  1463479359
a fabulous city always plenty to do be careful what area you stay in as big differences exist across the city
15  1463479582
everything was perfectjust too expensive
1   1463479898
highly expensive
96  1463480154
no need to say much about this magical city i have been there countless times and again it had a special atmosphere unfortunately millions of other tourists seem to feel the same as it was very crowded endless lines at the attractions about 200 people tryingt to get a glimpse of the mona lisa at the same time i guess may is not the best time; try winter
9   1463480901
great city with lots to experience and take in the visit to the louvre alone was a lovely whole afternoon    we tried climbing the eiffel tower via the staircase it&#39;s tiring but a lovely experience    get familiar with the metro lines they&#39;re the absolute best way to get around the city and the service is quite inexpensive    monmartre where we stayed has a great range of restaurants bakeries and bars
6   1463481145
there are no words to describe paris well enough if you haven&#39;t been to paris you should if you have been to paris go again
95  1463481428
a lot to see many attractions very fun walking around the city metro is so good gets you anywhere fast and always arrives within several minutes  for young people wanting to enjoy nightlife - 11th district  bastille - expensive shopping some good findings  luxembourg &amp; tuilerie gardens - very pretty  arc of triumph - pretty sunset upon the futuristic city    be aware of all the terms of your metro deal because the train inspectors fined us 33 euro each for not having a photo!!! that within a 10 meter distance of a photo booth - awful people the person at the train station didn&#39;t mention the picture when we came back to complain they just said that they didn&#39;t have to tell us and that the inspectors were mean
6   1463481930
i absolutely loved this city!  i did 2 tours around paris and was able to get the best views of the eiffel tower on the boat during the light show  i also made sure to make my own perfume - which was an amazing experience!  i wish i stayed a bit longer because there is sooo much to do but i will be back!!
12  1463482889
opera eiffel tower  arc de triumph river cruise basilica louvere
59  1463483463
paris is a beautiful city  shops and entertainments should be open and available in sunday too like in other big european cities
99  1463483901
majority of places i went to were under construction; louvre arch la defense sacre coeur   be wary of pickpockets especially in crowded area like in the metro station police officers can&#39;t really do anything if you get pickpocketed
76  1463485618
the travel from and to the airport took longer than expected due to the technical problems of ratp
78  1463485675
there are so many amazing things to do in paris first the various visits where culturally the french capital is quite famous for but over and above there is a rich cuisine and buoyant night life which worth experiencing  the metro system is fine and you can easily go around the city just a recommendation to take a 3 days pass or so to move quickly   the sacre coeur is one of the best pick! :)
28  1463485817
do you really need to review paris????  :-)
58  1463487726
it was easy moving around paris on foot and public transport enjoyed my trip toeiffel tower screat heart catedial  and the louvre
69  1463488723
the city is so green the parks are so nice!  people were friendly!
71  1463490339
great places to visit the open top hop on hop off bus is well worth it
54  1463490416
lots of beggers and homeless people wasn&#39;t nice to keep being asked for money it&#39;s a real shame
54  1463490590
i don&#39;t need to review paris it speaks for itself so go and take a look
86  1463490619
would recommend fat tire evening bike tourfantasticfuninformativehighlight of a great weekend
67  1463490639
batobus is great value esp with discount coupon in &quot;hotel&quot; magazine
83  1463490773
i can&#39;t pick one place because we enjoyed everywhereit was very easy to get around paris plenty of places to eat
32  1463491121
beautiful city looking forward to coming back!
91  1463491991
love paris!
43  1463492172
beautiful buildings and sights to see the seine can be very nice to sit and relax very busy city and people don&#39;t take notice of where they are walking which can be very annoying but overall a very enjoyable trip
91  1463492648
cafes behind every corner beautiful architecture culture and general paris feeling!do i need to say more?
35  1463493027
paris is a one and done city it was dirty everything closes early and very expensive the majority of people are rude the biggest reason to visit paris are the landmarks
47  1463493425
great day walking along the canal martin and around butte chaumont parc pleasant sunday flange ring around paris
77  1463493464
i advice to everyone to go for a brunch in sunday to belle villoise next to gambetta station  you pay 29 euros for a buffet brunch and jazz music
55  1463494258
people are friendly the food is fantastic and the sights are beautiful
41  1463494445
it is a beautiful city
92  1463495359
eiffel tour and river seine cruise were our favourite
98  1463496251
this city  enjoys being itself unlike london it hasn&#39;t let greed and developer destroy it charm and beauty unlike london it hasn&#39;t lost its self respect or sold its soul
41  1463496726
i loved the city but some people were so rude? am i missing something? anyway i treated it with wine and fine cheese :)
31  1463498143
bank holiday weekend so a few restaurants were closed we found a lovely chinese restaurant close to the hotel open for lunch and dinner saveur de printempsperfect!we found the metro very easy although sometimes involves quite a lot of walking to change trainsthe eiffel tower looks at its best at night as does the notre dame cathedralbook well ahead if you want to go up the towervery long queues and very expensive for drinks les invalides is a great museum and napoleons tomb very impressivethe paris bus tours hop on and hop off is a really good way to get round and there are loads of watering holes en routemost places had a little english spoken
13  1463498316
the ambience of the city is unsurpassed
39  1463498558
bought the hop-on hop-off bus ticket best way to explore paris it goes to the main paris attractions and we used it as means of transportation as well
9   1463498985
paris is a great city and just wandering around is a pleasure in itself
83  1463499092
excellent as ever
19  1463499852
i loved being in this wonderful city i found it easy to get around on buses and trains and liked the metro for it&#39;s speed i saw many fantastic buildings and famous places but will have to return again as there are so many more too see i can see myself having many more holidays in beautiful paris
54  1463501189
great city to visit but very disappointed with trip to versaille waited 1hour 40 minutes to enter palace and there was just to many people to make it an enjoyable experience    compared to a previous visit to peter the great palace st petersburg this came a very poor second both in terms of the organisation and the quality of the restoration work
8   1463503320
exhausting as so much to see  we bought a 4 day museum pass which was well worth it as we visited a lot of art galleries museums etc  paris is very walkable if staying in the centre
0   1463504808
be vigilant of pickpockets and black men who have decoy methods to steal your belongings   metro easy and cheap to use but it&#39;s easy enough to walk  about  if you want cheap drinks and  food then head to latin quarter
73  1463505471
too many sights to mention bars&#47;restaurants in and around montmartre&#47;pigalle area wonderful   paris at all times of year is magical but cannot beat a good warm summers day in paris
84  1463505804
paris is amazing but bra ware pick pockets and scams are everywhere would advise people to be on their guard at all times
19  1463506428
always felt safe - people were very friendly  if you pair up your must-sees correctly you can cross a lot off of your list in a few days!  i would recommend the seine tours at night  the subways are great too!  we walked about 12 miles every day - over 30000 steps - but every view was worth it
93  1463507122
paris a city of wonders so much to see and do i can never get enough of paris excitement day and night dozens of sights to visit notre dame tour eiffel arch de triomphe etc
45  1463507157
no comment
73  1463508141
so much to do and just a great place to explore
62  1463509487
lots to do like anywhere target your trip to a few specific things and do them properly go to a cabaret and do all the landmarks take sensible shoes and buy day or multi-day metro tickets     you won&#39;t do as many museums as you think you will so if you get the museum pass really target the museums you want to see    there is much high visibility poverty in paris these days poor guys
54  1463510155
unfortunately paris isn&#39;t what is was we visited second time two of the great museeums- orsay and picasso- everythink was changed we couldn&#39;t find many of the masterpieces  montmartre was allmost empty deserted
85  1463510595
amlpe historical sites
67  1463511926
gare du nord area is nasty rest of paris is lovely albeit very expensive and touristy i recommend moulin rouge at least once in your life time
82  1463512253
excellent cave ÃƒÂ¡ vin on ile saint louis l&#39;ettiquette talk to hervÃƒÂ© about his passion organic biodynamic and natural wines then indulge in a tasting
53  1463514367
restaurant at the eiffel tower was excellent!!
61  1463516875
la belle ville
45  1463517762
paris is a hard city metro is useful but it&#39;s dirty and full of homeless people    the worst part was a fine i had to pay on luvre museum metro station since i dropped my ticket there were some sort of police officers asking everyone that already exit the train and  ready to get into the museum for the used ticket failure to show it results on a 50 euro fine that i had to pay right away sad for tourists since there were like zillions under the same condition behind us the officers didn&#39;t care if we were tourist or no so everyone here don&#39;t throw your ticket away
72  1463519132
beautiful historical city lot&#39;s of monuments to see good for a leisurely walk down champs elysee cruise down the sine river go atop the iconic effiel tower spend hours in the louvre - mona lisa et al go crazy shopping  travel by train from paris to marseilles or lyon sooth your eyes with all the green landscapes
8   1463519439
transit traveller would stay two days the next time  no queuing tickets  for louvre and muse d&#39;orsey were offered which i was sad not to make use of
84  1463519654
unreal
82  1463519819
if you&#39;ve never been to paris you really need to go you will not be disappointed  so much history great destination
48  1463523161
lovely people managed to see a show at the moulin rouge and a boat ride on the seine with a meal walked a lot and used the tram buses and metro too got lost on the latter a few times but well worth a visit
36  1463523490
been to paris previously still love it paris has a great romantic relax feel about it and is always enjoyable it is easy to get around on public transport and always plenty to see and do
68  1463525847
paris is an amazing city with plenty of places to visit; either you decide to follow a strictly planned schedule either you just stroll down the streets you will have an amazing time!  few tips: the louvre is huge! if you have few days only do not even think about visiting it it takes one whole day and you will be exhausted at the end of it! if you love art why don&#39;t you visit the musee d&#39;orsay instead? the tuileries gardens are a definite must see: you can sit down and enjoy the artificial lake with plenty of ducklings and other little birds and the wheel you cannot go to paris without going up the tour eiffel: it&#39;s the most amazing feeling ever!!!!! try not to eat in touristic places; avoid restaurant and bars full of foreigners and head down for the ones where french people are: those are he best! and for breakfast many boulangeries have deals: i had coffee and croissant for Ã¢â€šÂ¬180!!!! the only drawback for me are the metro and the bus stops: directions are not clear enough!
35  1463531794
beautiful city!
69  1463537481
ofcourse eiffelvtower could not find any vegetarian restaurant near eiffel tower one will find many poeple trying to fool tourist which is bad but overall great city nice and friendly people
46  1463540086
so many things to see but so little time
99  1463542872
metro is very easy buy tickets in packs of 10 allow at least a week in paris to enjoy all it has to offer after seeing the &#39;must sees&#39; the best of paris is exploring all the side streets of each district amazing shopping and traditional french food my advice would be not to eat in main tourist areas much more expensive and not as authentic
50  1463550387
amazing old cityhas a magic
23  1463553595
st michael is one of my favourite places in paris lost of street bars and restaurants nice food stores and good for money
35  1463553730
paris is awesom the paris peoples are rude - waiters sales man etc just don&#39;t anticipate good service  they have zero tolerance to tourist :)
67  1463554610
nice view of city
49  1463555603
i liked people and streets! and a lot of things to see!Ã¢ËœÂº
36  1463557172
paris is an amazing city and you can spend all day just gazing at the architecture &amp; the parks do stuff that regular tourists don&#39;t do go on a cycle tour do a walking food tour go for the bicycle tour to versailles palace highly recommended skip the regular attractions &amp; live in paris for a couple of days like the regular parisiens by doing a picnic in the parks with baguettes wine &amp; cheese spend a day at the gardens with a book &amp; coffee extremely charming &amp; cute! go for a walk or run by the lake or canals so many things to do!!     lastly don&#39;t forget the segway ride one of the highlights of this city awesome way to see all attractions &amp; have your adventure too!!
15  1463557478
we walked everywhere (and worked up an appetite for dinner which was easily available on most streets)  locals very friendly if you try to speak french
88  1463560056
i had fine days in paris - only problem was the low temperature a little shopping in the 6th arrondissement nice meals at chez andree and general relaxation
59  1463560104
we were visiting for the rugby 7&#39;s tournament at jean bouin stadium so didn&#39;t do as much site seeing as previous trips my partner had never visited paris so we saw the eiffel tour at sun down which was magical notre dam cathedral walked along the river and had a general amble about exploring the different areas paris is an ancient place full of amazing architecture and rich european history went out for drinks and a dance in bastille which can be a bit wild but fun but good for clubs and live music
36  1463562723
very easy getting around on the metro most of the popular places were very busy also with a lot of street sellers and others trying to con money out of you just use your wits and be careful and all will be well look out for markets in parts of the city these are well with a visit food is very good
83  1463563416
awesome and i will definitely return soon
4   1463565486
great apart from weather
58  1463570497
this is the city of love every bit of the city is amazingly beautiful    however the price for everything is a bit more expensive than other european countries
4   1463576278
paris is by far my favourite travel destination has everything good food history parks i just love it!  we took the batobus from one sight to another which was more practical than the metro with kids though it is really expensive (17eur pp)  the best you pack a sandwich and enjoy the parks at midday when our toddler was asleep!
34  1463581344
brilliant !!! but the trip to the evening show at the moulin rouge was a bit of a disappointment very expensive and not that good ( cruise ship entertainment )
51  1463581992
it was pariseven under a state of emergency i felt no sense of danger loved walking and from my hotel everything in the area (eiffel tower; arc de triomph; notre dame etc) were all within an easy strolli will be back and for a longer tripa week at least
85  1463583444
it is never disappointing to visit paris!and yes please go visit the museums just remember to book tickets in advance to avoid the long long queues
72  1463588466
bus tours were excellent also the metro so easy to move around parisfood was quite pricy
97  1463589311
great city nice view and culture
23  1463593536
great bike  and boat tour
83  1463595366
obviously a fabulous city  although i didn&#39;t travel with my children i did notice it is not pram friendly  also watch out for the quick doors on the metro train it would be really easy for a child to get trapped on one alone!  ladurÃƒÂ©e is a must!  musee d&#39;orsay also  go early and hit van gogh first (up the stairs and on the right) then head straight to the impressionist  it gets busy quickly and you want to be able to appreciate these works from a distance which is not possible with the crowds
81  1463602930
all good very friendly across the board  not normally associated with paris from ticket offices at attractions to brasseries and bars even taxi drivers were friendly and helpful
80  1463604556
paris hasn&#39;t changed too much but the people have they are much more friendly than they used to be 9-10 years ago  now paris turned to be a perfect place to visit one of my favourites
4   1463605826
paris is only one!
39  1463606624
quite nice but what out for the added vat on items
8   1463607173
we walked and walked and saw most for the sites but did not have enough time to visit the contents inside as a result we will have tio  return  in the future
88  1463607243
beautiful european capital with a wealth of history and architecture  vibrant city where everyone is on the go many good restaurants and great shops
33  1463607476
too much to do try a quiet walk through montmartre rather than the champ elyssee versailles is a must (take the passport and book a tour to the private rooms of the king)
9   1463621173
i expected tasty food since france is well known as a delicacies country but the food was not good at all ( netherlands belgium is much better than france) and public transportation is not clean &#47; people in general were not friendly and arrogant i only enjoyed a eiffel tower in france
65  1463629898
can recommend the paris canal trip- 25hrs from parc de la vilette to the musee d&#39;orsay via the canal de st martin it takes you through 9 locks down 27metres the historical commentary was excellent we used pariscanalcom
93  1463633112
so many amazing sights and the shopping is great prepare your walking legs as you will use them a lot
3   1463639901
travelling with family was a nightmare in paris most of the stations didnt have escalators and taking around baby stroller was a teadous task   the metros could think and plan better for elderly and kids
97  1463645132
master the paris metro!plenty to see in every district of paris  have a relaxed afternoon at a cafe
27  1463645242
it a touristic city that you can enjoy a lot of activities in though out the 24hrs
14  1463645397
paris is a beautiful vibrant city sure it&#39;s full of tourists but it is the most happiest city i have ever visited i found the french very helpful and friendly
79  1463646571
i walked most places as my hotel was very central   i felt safe walking around as a solo female traveller i ate at different cafes each day with no bad experiences i did small tour group walks around various places my favorite place was versailles
15  1463648100
eiffel tower
15  1463648160
didn&#39;t like it that much  very crowded and dark for my taste
99  1463648627
paris was amazing! although the weather in may was still a bit cold overall it was ok i went together with a quite large group of friends and we stayed for two days the first day we visited montmartre and sacre-coeur  notre-dame musee d&#39;orsay and eiffel tower we had lunch at a restaurant in the latin quartier the second day we spend most of the day in louvre and in the afternoon we just walked around the centre of paris and along the banks of seine if you have time you should deffinetely go on a boat-trip on the seine sadly we didn&#39;t have enough time and that is the only thing we regreted not doing
69  1463649775
loved the cityconvenient to all major attractionseasy to get aroundpeople were friendly
25  1463649881
the boulevards ponts shopping sightseeing ease of travel using the metro landmarks partisserieswe stayed in the 11th arrodisement - felt like a local - 50m from the bataclan - vibrant close to everything by metro good location
58  1463652534
extraordinary love it
76  1463655404
dirty comparing with its past specially touristic areas around les halles and trocadero metro stations need more attention and laws respect
97  1463655516
always a nice city
21  1463657094
love st germain atmosphere cheap dinner menu in the restaurants there
62  1463657227
taking a rest at the luxembourg garden is great  i recommend visiting montmartre in the morning because it is more peaceful and quiet then in the afternoon
69  1463659069
paris has great tourist locations but there are a lot of people who prey on tourists i dont feel safe in paris pick pockets are around &amp; people are not honest
44  1463661790
we packed alot into 4 days and enjoyed every minute
6   1463662410
all streets are very inteesting and lovely!
2   1463663129
so many places to see you need to plan which one you would like to visit first we went on a church visitnotre dame we went to versailles  quite far but its worth the visityou need to try the bateaux ridesee eiffel tower at night and you need enough time to visit the museaums
92  1463663972
felt expensive however maybe not more than other capital cities just seemed to be  great ease of getting around taxis quite cheap and plentiful  metro reasonable 11 euo for a day passloved mont martre  loved islle saint luis  thought the buildings and culture were amazing  might not spend the money on the river cruise you can see it all on foot  eat at st regis on isle saint luis  58 at eiffel tower was lovely for my daughters birthday  book early for danard a fromagerie off rue rivioli we could not get in  angelinas for breakfast &#47; brunch was amazing  2nd best eggs benedict i have ever had outside of canada splash out and do it well worth it
78  1463664447
three full days and two half days which included:  montmartre louvre d&#39;orsay eiffel tower versailles catacombs museum of middle ages latin quarter and a big bus tour    travelled by bus feet train and metro very easy to get around  buy a paris pass for 1 2 3 or 5 days for all travel around zones 1-3  (versailles zone 4 we got as far as we could on the paris pass ticket and then got off at viroflay to get a return train ticket to versailles and then back again via the paris pass    for any attractions do try and book online for your tickets - the queues in paris are long!
75  1463665004
lovely city  some waiters can be rude and impatient but paris isn&#39;t unique in that  just enjoy the sights and the food and spending the euros!
5   1463665525
paris in april - the flowers were blooming and the city was alive  i found my visit this time much less populated by tourists than previous visits there were no line ups at the louvre the eiffel tower notre dame or any other tourist destinations it seemed most of the other tourists were in fact french speakers  the city has so much charm our favourite activity was always spending the afternoon at a bistro cafe enjoying the simple things - good food and wine all of the french people we encountered were sincerely friendly and polite i had well dressed strangers offer to help while we stood with a map in the metro station it helps if you speak a little french so try to learn the basics if you don&#39;t speak the language beware of pickpockets - they exist i encountered one at the sacre coeur but the police were quick to respond  paris is a working city - not a fairytale destination but everywhere you turn is beautiful put your walking shoes on and plan on travelling by foot as much as you can to experience all the different areas of paris we loved the quaintness of the latin quarter found some great little bookshops and bakeries   our favourite dinner was at le lobby restaurant in the peninsula hotel we were a group of 10 and were treated like royalty we chose the menu marche option and splurged on the wine menu a sole pianist entertained us all night with beautiful songs to which we all danced between courses what a beautiful night to remember and i will certainly return there for dinner with my daughters in the future
35  1463666269
big and good city but metro was pretty confusing for me since i was not used to it once we know it is good need to see city both in day &amp; night should not miss both the views  the monuments and towers were beautiful in the night light
74  1463668926
luxury shoppingÃ¯Â¼Â  but the cleaniess of the city is a bit poor
10  1463669018
food! even if you&#39;re not a gastronome the choice and variety and quality of all the little eateries is outstanding
26  1464964352
loved the eiffel tower and other sightseeing  louvre was closed on tuesday so slightly disappointed but will visit on next occasion  food was lovely everywhere we went
83  1464966122
my favourite places were the eiffel tower the louvre chapel of our lady of the miraculous medal and notre dame cathedral     all my favourite places above were easily accessible with the metro making it very convenient  however i must say the metro is not very suitable for senior citizens as not all the metro stops have escalators making it tough for senior citizens to climb the staircases to get in and out    there is a very good italian restaurant called casa di mario nearby the chapel of our lady of the miraculous medal the pizza there was very very good :)
60  1464966911
very expensive amsterdam beats it hands down in summer and is friendlier
60  1464966961
no more words needed for telling you how great paris is the major problem that i had during my stay was that you just couldn&#39;t find enough toilets in paris which is a huge disadvantage while you were in desperate to excuse yourself late at night
21  1464967251
easy to get around the city lovely sights and good restaurants however some of the people were rude and very abrupt
43  1464968237
very easy to get around using metro  would highly recommend bateaux parisienne lunch cruise - food excellent and a very enjoyable experience  fat tire bike tours also good way of seeing city
12  1464969117
we walked to le louvre enjoyed the museum very much we also took a train to the eiffel tower and enjoyed a panoramic view of the beautiful city and finished off with a cruise down the river seine
94  1464970615
i&#39;ve been to paris four times and it gets better each time
34  1464971364
beautiful city - architecture art history restaurants river historic sights
55  1464971421
paris is a grand city and easy to get around by train or foot if you like walking we loved the army museum walking around the notre-dame and through the jardin des tuileries as well as a cruise on the seine with vedettes at du pont - neuf
56  1464971962
paris is a beautiful city with amazing monuments museums parks etc  we did walk most of the city  we went to disneyland and it was a disaster at least six or seven attractions were closed plus it was raining heavily all the time  paris has a great offer of restaurants personally i would recommend the brasseries
73  1464973199
we loved the fact our hotel was close to the metro so we could explore walked up to the sacre coeur spent whole afternoon exploring the area went to the artists square which was very crowded never thought i would have to fight to get on a pavement !! visited salvador dali exhibition which is well worth the entrance fee amazing works of art my husband was entranced  went to le chat noir restaurant for dinner which is next door to the hotel  we had a lovely meal at a very reasonable price  went to the eiffel tower next day via the metro there was a busker  playing the violin in our carriage  gave him a few coins  after the eiffel tower we caught the bateaubus to other attractions such as notre dame  and the louvre the view from the swimmer is amazing the bateaubus tour is two hours but you can get on and off as you please back in montmartre  we went to flunch for dinner this is a self service restaurant serving delicious food we spent 37 euros on a three course meal  for both of us how it works is you choose your dessert  and drink then choose your main course starter or salad then pay for your meal and then choose your vegetables chips and sauces veg chips and sauces are unlimited  and you can refill your plate as often as you like  we spent a lot of time exploring on foot  found some lovely shops  and cafes we found paris easily accessible via walking and public transport  the metro is easy to use if you can&#39;t use the ticket machine there is ticket office open where you can buy tickets by debit or credit card  machines take card or cash bus drivers are friendly and happy to give directions   metro tickets can be used all day on buses boats and metro   paris is a fantastic city to visit and it is possible to see all the tourist attractions at your own pace over three days
67  1464973327
i would recommend booking places of interest such as eiffel tower or arc de triomph in advance as saves a lot of time queuing     used the metro alot to get around paris and was great value for money
19  1464975058
so many places to visit and just beautiful to walk around  some great deals for eating too  very helpful people
73  1464975713
lots to do  very expensive
2   1464977995
i have been here many times i like to check out my favourite places and wander and find new ones i usually try to see any of the new exhibitions i usually get around just by walking although this time i also used the busses because of all the rain
54  1464979126
outside paris great paris its self the pits
82  1464979907
paris has an easy to use transportation system (metro bus uber taxis)  the hop-on hop-off bus is expensive and does not provide close access to many of the attractions easier to take the metro but then you miss out on the commentary  a trip on the river seine is a must  recommend you purchase a multi day museum pass if you plan on visiting 3 or more museums as you get to stand in a priority queue  in the case of the louvre it is nice to be able to break your exposure down into multiple visits as it can be overwhelming  the pedicab drivers were informative and fun  they also will bargain with you
53  1464980722
a beautiful beautiful city full of art great food fashion and culture there&#39;s so much history in one place you could live there for years and keep on discovering new things and new places paris will always be somewhere i will keep coming back to the food pastries and sweets are incredible and there&#39;s something to be said about the parisian people a certain sophistication and indifference to negative opinions adds to their seduction perfect city for walking and getting lost i will always keep coming back
9   1464982456
we loved the broad boulevards and the beautiful architecture we ate local cuisine in small cafes and they were all friendly and stocked excellent wines we walked most places and took cabs which were reasonably priced we love museums and art and there&#39;s no better place than paris
80  1464985276
loved monmarte the most went there every morning and evening nice spots to have foodwalk or just chill  be careful tho around cathedral in monmarte to walk too far away from touristy places there is lots of hussling going on and if you are watching closely you can notice very interesting things going on hehe but again if your bit of andventure seeker and adrenalin junkie this area will pull you in!
72  1464985343
without a doubt my favourite city in my favourite country to visit what is there not to like the iconic landmarks like the eifel tower notre dame the louvre and the musee d&#39;orsay then there are the restaurants and the cafe&#39;s and the brasseries where you sit outside and people watch paris is a busy city with something going on all of the time shopping walking drinking dining - it has the best of everything and in all price ranges i am never tired of paris it is the best
0   1464985485
managed to see all the major sights and not be struck by lightening or flooded!
5   1464985884
lots of walking but it is one of the better ways to see everything we stayed off the metro competently found it to easy to get turned around there was a cafe very close to our hotel we ate one meal a day there it was very nice the staff was humorous and had fun with our struggling french
97  1464988436
inexhaustible supply of things to do  terrible weather (currently flooding just after our stay)!  one of the most beautiful cities i have ever been to  occasional arrogance of shop assistants a disappointment countered by great friendliness of restaurant staff  i would go there time and time again if i had the chance and never be bored
8   1464988676
great history streets in bad need of a good cleaning
55  1464997640
eifel tower n the river  ate muslim food can get nearby hotel  easy getting around by metro  none
67  1464999974
so much to see and do it was so easy to get around using the metro the variety of food was great and everybody was so helpful and friendly
77  1465002171
you must go to the le pur restaurant at park hyatt paris!!
55  1465005123
montmartre is my favourite place in paris for me all other &quot;attractions&quot; were interesting to see however montmartre is where i &quot;felt&quot; paris
12  1465014293
the sacre coure  eiffel tower and all the bakeries and streets and our boat trip on the seine
15  1465019265
beautiful architecture interesting street designs wonderful art everywhere  subway was great  crowds were manageable  food was fair except for desserts and breads
74  1465021448
strike interruptions soured the trip
19  1465024177
lunch in 58eiffel is a must! segway tour was an amazing way to see all of the highlights quickly use the metro instead of expensive cabs
62  1465024826
louvre  eiffel tower  sacre coeur   notre dame  moulin rouge
35  1465026237
paris is great for everything  this is our fifth visit to paris and we can always find something else to do even if it is revisiting places like the eiffel tower or the tulieries i think it is true that if you are tired of paris you are tired of life  even though the weather was terrible there is so much to do and so much to see that this is not significant  the food is generally wonderfula little too wonderful sometimes although beware of the prices in the super touristy areas  walk a couple of streets back and the prices often halve
30  1465030138
what a beautiful city we travelled with our 7yr old who absolutely loved it we visited the fantastic museum of natural historyhad a trip up the eiffel tower enjoyed riding the metro and spent a wonderful 3 days soaking up the atmosphere of paris  the only negative was the folk trying to sell stuff around sacre coeur they were grabbing us pulling our coats and one even grabbed my son&#39;s hand i presume they are doing this illegally as they scarpered very quickly when the police arrived!
70  1465030873
good food accessible transport loved all the sights
31  1465032455
eiffel tower
40  1465033205
i like the eiffel tower and the louvre museum    i do not like the traffic and the seemingly labyrinth type of subway
78  1465033248
we went to pais to go to the french open tennis at roland garros we were lucky to get two days of full play however the rest of our stay it rained we walked around quite a bit and had a lovely lunch in a restaurant we just happened upon during a heavy downpour we sheltered in a doorway and after a minute or two a very kind lady let us into the hall of a block of apartments to keep dry soon we were joined by other tenants and had a lovely exchange with them there&#39;s so much to see especially if you like architecture and history  the metro was easy to use and ecomonical with the 10 ticket offer for 14 euros
66  1465033567
paris is over rated  very expensive and not very friendly people very insecure i was looted twice by taxi drivers
93  1465037329
most beautiful city in the world!!!
44  1465038611
architecture
57  1465039684
we bought a 2 day pass for the open top bus &amp; the batoubus river boat well worth it   loved the ambience around the pompadour centre great restaurants &amp; bars a must is galleries lafayette shopping paradise
83  1465043342
be careful near eiffel tower area lot of pick pockets and cheats they cheat you in many ways one of the famous trick is ball and cup game they will give double money if you win they cheat you like anything and they play with you as a group please avoid ball and cup game in paris everyday many tourists are losing thousands of euros with these cheats you cant complaint about them with police too if you loose money
45  1465044521
paris is lovely just a shame about being harrassed by people selling sovienirs and felt a bit unsafe with the military around but other wise was good
81  1465045574
having been to paris on many occasions we know how things work we revisited one or two restaurants and locations sadly the incessant rain meant we could only do so much even the river trips were affected as was business at many restaurants but we still enjoyed ourselves our tip is explore soak up the atmosphere enjoy the food and occasionally go off the beaten track and see what you find every time we go we try something different last year a trip down paris&#39; sewers a bit wiffy but fascinating this year we popped in to notre dame cathedral just as a mass was beginning it was almost like being catapulted back into medieval times with processions of priests worshippers and clouds of incense chanting and ringing of bells we&#39;re already planning next year&#39;s trip
76  1465046783
paris is amazing!
31  1465049816
the best thing in paris is its transportation particularly the network of rer trains and metro which is much cheaper than london moreover most of the city attractions can be travelled by walk connectivity to disneyland and versailles is also great with frequent rer trains we found that  buying ticket booklet was better than a day pass    we mostly had sandwiches and carried water and fruits with us we also had dinner at saravan bhavan (south indian vegetarian restaurant) near paris nord station    though we didn&#39;t got the best weather it was continuously raining for all six days of our stay we liked the trip we took the hop on hop off river cruise on batobus one day and covered notre dame grand palasis petit palasis place de la concorde alexander bridge and champ de mars we also covered the eiffel tower louvre museum sacrÃƒÂ©-cÃ…â€œur basilica arc de triomphe  on other days    visit to  disneyland and versailles were also exiting we stayed two days in disneyland though it was a bank holiday we were lucky not to have too much crown at disneyland due to poor weather we could cover more attractions in two days than we had actually planned for
3   1465054639
just love paris - the atmosphere is great!!  just hope the flooding subsides soon  we like to eat in the latin quarter - either the &quot;bourbon&quot; or the next restaurant along from there  as we go on a regular basis they really look after us  there were three in our party so apart from walking we found it more convenient to get around by taxi
85  1465054996
it was okay to find good food
54  1465056503
i have been over 100 times and never tire of paris always thrilling vibrant and romantic very rich culturally be it the monuments museums or the classical music scene   easy to get around by metro and by bus on foot and taxis are reasonable
74  1465056632
paris? it&#39;s got everything
17  1465057962
great location and a beautiful city  loved it!
90  1465059328
weather did not help to browse more of paris we came from luzern of switzerland and it seems that leveled up our expectation of paris it was crowded and just ruined  the beauty of paris
52  1465060539
fabulous city amazing sights to see very easy to get around walking local bus service or metro super expensive drinks of any type
70  1465064573
couldn&#39;t fault our time in paris except for the freak wet weather which has sadly resulted in such devastating floods this however didn&#39;t stop us from getting to see lots of the sights  paris is extremely easy to navigate; we mainly used the metro which is cheap and quick and gets you out of the rain    the wonderful restaurants cafes and bistros on every street corner (and more!) are consistently good  our primary reason for visiting was to watch the french open however we combined this with sightseeing and experiencing as much of the city as we could  paris is not to be missed!
52  1465065141
the best place to be there for a day or for the whole month
41  1465072591
strik!limited run for rer
73  1465082583
dineyland is very good
60  1465091575
two day museum pass worth every penny love all the museums especially rodin
66  1465095958
walking along the seine viewing the eiffel tower  shopping  the kids enjoyed the hard rock cafe and we used taxis ( reasonable ) and the metro was easy to use too
22  1465105667
we were enjoying paris until the train strike where we were stranded  i had my purse stolen at the railway station  we had to find accomodation and pay for accomodation we had booked ahead    favourite places  the lourve   bus tour to monet garden wonderful day  people helpful  train information confusing   organised our own eating some cafesyum cha was good
11  1465107917
highly recommend hop on hop off bus
78  1465110055
eifel tower floating park on the river
30  1465110969
vert beautifuk city
59  1465111735
too many places of substantial interest to mention but nearby the hotel you have pantheon notre dame ile st louis jardins de luxembourg and the beautiful bohemian quartier latin all around you
0   1465112510
aah paris always loved it this is my fourth time here of course there are frustrations - the french do not know how to manage queues and crowds the way americans do - check out any theme park or museum in america  however the french do know their art food and architecture even if they have to import it from italy and egypt
44  1465119165
public transportation and friendly people
78  1465119617
paris was great only issue is even though it&#39;s a tourist destination no one speaks english!!!
67  1465120499
just visited in roland-garros tournament and that was great
22  1465120903
paris is so beautiful  weather was awful and unfortunately the strikes had a negative impact on our trip but the people were friendly and helpful despite my appalling french!! we were in paris for a concert so didn&#39;t do sightseeing (except from the taxi window) ate twice at mastrianni italian restaurant a few minutes walk from the hotel  highly recommended  delicious food and friendly service at reasonable prices and some english spoken
53  1465121336
the city is really nice but france in general is not very tourist friendly everything is in french!
29  1465122571
if you have a very short stay and too tired to walk just take bus 52 right by the hotel and get a good glimpse of paris !
82  1465122938
paris is paris nothing to add
5   1465122953
eifell tomew the louve champselyseesplace of clichy  and the other tiurist atteactions were done on the hop on hop off busit was easy getting around the different areas by metro
93  1465123552
lots to see and do getting around is easy eat a few streets away from the tourist areas for better value food but everywhere we&#39;ve eaten has been great we particularly recommend chez gladines near les halles as no tourists but get there early cos locals fill it up and queue for tables from about 8pm onwards
19  1465123678
loads of food choices for everyone recommend rue de st serverin for mixed meals at good prices jardin du luxembourg is amazing
40  1465124780
going to val de europe  i at restaurants arround champs
74  1465124851
despite the weather and the strikes paris is an awesome city which so much to keep you busy disney tip - download the app before you get there check what attractions (if any) are closed and then think about visiting walt disney park next door as that closes at 18:00 and disneyland paris closes around 23:00    if using the metro buy a billet of ten tickets it&#39;ll easily save you a few euros
56  1465126584
so much to see so much to do i will have to go back some day
38  1465127590
it is bit crowded and metro system is old some nice places to visit and food is little cheaper than other neighboring countries
11  1465127628
great walking city paris demands a repeat visit
58  1465128819
paris was alive we only had 1 day which was not enough   train services excellent lots of steps to get to the platform if you are elderly
43  1465128903
montmartre and the jewish quarters are nice the commercial streets are crowded by rich chinese beggars and vendors of cheap stuff which you don&#39;t need  metro is great but does not feel safe due to pickpockets  the traffic is a nightmare and very noisy due to lots and lots of scooters &amp; motorbikes
61  1465129261
good food and easy transport system
63  1465132008
best city ever!!
58  1465132021
just down from the sacre coeur were some lovely backstreets and cafes      lots of places away from main attractions worth exploring
3   1465132170
paris is fantastic so many things to do to see and places to eat we were just desperately unlucky with the weather
94  1465132213
everything
60  1465132387
paris is a very buzzing city but you need to have your wits about you when driving  best option would be to find cheap parking and use the metro  food is very pricey  staff in restaurants and hotels speak a wealth of languages and all were very friendly  allow plenty of time to see everything and be prepared to queue
39  1465132944
hotel was in a good location with restaurants and cafes a few minutes away the pantheon &amp; notre dame within 5 minute walk &amp; the louvre 15 minute walk do not get a taxi from the station they tried to charge a flat rate of Ã¢â€šÂ¬150
82  1465133077
like most cities you see it best on footdon&#39;t be afraid to use the metrovery much like london the seine was as great as ever! more so because it was in flood!
36  1465133213
paris is amazing city beautiful weather and air is clean ladies you can go on with your makeup all day   loved the city
72  1465133251
the old buildings were nice to see but we found the city to be dirty the weather was also very bad and we saw a lot of places flooding
15  1465133497
great culture   with free access to museums for children
22  1465133617
wonderful atmospheric colourful dynamic place bit smelly here and there as are all cities children loved it - best way to see it = walk!!
0   1465133837
we enjoyed going up the arc de triomphe and the eiffel tower  disappointed by poor cleanliness and abundance of beggars everywhere some being quite agressive and threatening  prices exorbitant even in some areas mainly used by french people  this was not the paris i remembered from 10 years ago and i am french!
32  1465134111
art and show
79  1465134748
take the metro to alma marceau when leaving the station immediately in front of you is the restaurant cafe francis this is a must for dinner as amazing view of the eiffel tower food superb and staff excellent
61  1465135079
you&#39;ll hear that paris is beautiful it&#39;s true - go and see for yourselves! it has a great atmosphere for a tourist; a lot of things to see and do no matter your interests be aware that the city is very big though so choose your hotel location wisely staying in the suburbs could mean 40+ minutes by train just to see the main attractions in the centre     an interesting fact: if you like asian food paris is for you there are a lot of good quality not too expensive restaurants serving dim sum sushi korean bbq etc a must see area is the latin quarter on the west bank (sorbonne jardin luxembourg notre dame pantheon)
1   1465135344
we were unlucky and had surprisingly poor weather in june this greatly affected our perception of the city everything is more less ok city is nice and full of museums     the risk of strikes affecting our stay was disconcerning otherwise city is easy to move around    we managed to find few culinary gems but also stumbled upon a lot of mediocre eating places
18  1465135716
expect to encounter pick pockets on every outing  leave anything you do not need in the hotel safe (money passport credit card) but keep copy of passport with you parisians are rude and unhelpfulness is their norm do not bring euro 500 notes which are rarely accepted and can not be changed in banks or post office  paris is a city with many colored immigrants so do not expect a &quot;french&quot; holiday
96  1465138703
so much to see and do great metro system when it&#39;s running properly get the metro pass for 21 euros and ride as much as you want 4 day museum pass for 62 euros was also worth it versailles = overrated go to fontainebleau
73  1465138819
go to svetlana&#39;s for great russian food music and vodka near abbesses metro on rue d&#39;orsel     musÃƒÂ©e carnavalet - free!!    vedettes de pont neuf - be a tourist it&#39;s ok!
20  1465138942
charles de gaulle took a long time to get through passport control - very poor
26  1465139302
people language
22  1465140502
good view but not a safe place
30  1465140853
keep you bag close to any part of your body hug them close to you even when you are eating&#47;waiting&#47;sitting etc
46  1465141171
went to see the french open tennis for 2 out of the 4 day&#39;s there so easy to get tickets and great to see the tennis and stadium   ate at a lovely small restaurant 2 nights called babalou just round the corner from the sacre couer quirky french design good staff lovely food   the other 2 nights ate in port d&#39;auteil nothing to write home about though   the metro is a great way to see the city a few closures of stations but nothing i couldn&#39;t over come   loved seeing the sacre couer at night it&#39;s a must   visited notre dame eifel tower arc d&#39;triomphe the louvre the usual tourist hot spots love paris can&#39;t wait to go back again
66  1465141566
get up early and get out there!
39  1465142266
even though it was cloudy and gloomy the view from montparnasse tower was great - would definitely recommend that we enjoyed trips to versailles arc de triomphe notre dame (plus tower and crypts) and pompidou centre also found pantheon very interesting    unfortunately we were unable to do a river cruise or visit the louvre or musee d&#39;orsay (closed because of the floods) so we&#39;ll have to go again (shame!!) big bus tour was ok but the commentary was poor - bad reception and too much crackly music    metro was a great way to get around
2   1465142406
eifel tower louvre champs elysees gardens of versailles notre dame and view from  sacre coeur
91  1465143390
we loved monmarte even though touristy still pretty &amp; fun we had a bicyclette ride back from the eiffel tower we were peddled along the seine and through the paris traffic fun we walked to the top of the arc de triomph to watch paris light up as we couldn&#39;t go on a night cruise bcos the seine was too high
56  1465143968
the atmosphere around place de la bastille was vibrant with many great restaurants and other attractions  travel around the city by met is easy especially using the no 1 line which got us to all the major attractions  museum d&#39;orly was closed on the monday we had available to visit  it is a shame they are not open 7 days a week  we also wanted to visit monet&#39;s garden - heard it is fantastic - but it was difficult to get to by public transport as it is out of paris  we should have used a pre-booked tour  our visit to the jules verne restaurant at the eiffel tower was a highlight - a very expensive long sunday lunch that was truly exquisite
84  1465145621
great archiotecture
13  1465146383
Ã¢ÂÂ¤Ã¯Â¸Â
99  1465146636
paris has beautiful monuments huge and popular museums however this supposed to be a great capital in europe is lack of green areas far away from cities like london and madrid several metro stations and nearby areas are like building sites or unfinished works several areas seem to be very dirty particularly the 18th arrondissement which includes the eurostar - gare du nord station and the famous sacrÃƒÂ©-cÃ…â€œur the city is quite expensive as for example having a regular coffee near of the notre dame cathedral it cost more than 5 euros! in addition i have the feeling that french people smoke a lot not caring at all about to keep a healthy life!     overall i have a poor rate for paris i was expecting a bit more from this famous and romantic city
8   1465146661
a beautiful city with many attractions too many to cover in one weekend spoilt for choice when it came multiple cafes and restaurants     metro system is very easy to use and travel throughout the city
47  1465146792
everything was just excellent
90  1465146858
big city  once you&#39;ve seen the big monuments what strikes you is the crazy pace of this city the not very polite people and their rudeness  but then its probably the same in most big cities  what i find is that parisians have no shame  they can hold up traffic or a whole load of people on a train and they don&#39;t give a hoot  not my kind of people being from canada   france is great and people are much nicer in the regions than in paris  worth seeing the country but skip paris or minimize your stay to 3 days and that will be enough
19  1465147047
planning metro routes beforehand batobus 2day ticket good idea for sightseeing  hop on and off and walk
33  1465147196
it was not our first visit to paris so we dedicated the first day to visit louvre and quai d&#39;orsay but we did not visit again other monuments (tour eiffel and so on)  we used the following days to visit other musea such as the orangerie picasso rodin marmottan and nissim de camondo the sainte chapelle is something incredible you cannot miss it!   very nice eating in &quot;le relais de l&#39;entrecote&quot; et trÃƒÂ¨s bonnes crÃƒÂ¨pes bretonne en rue de canettes  we moved by metro buying a 10 billets carnet t+ (most convenient way to go around for few days) and a museum pass for 4 days
44  1465148659
don`t it
51  1465149383
fooooood
13  1465149468
we liked the louvre and quai d&#39;orsay museums the eiffel by night the tuileries gardens we enjoyed the hop on hop off bus the problem was the heavy rain  we ate at some brasseries near the hotel but mostly in out room because we traveled with children we had a kitchen and we cooked easy meals   it is very easy to get around paris with the paris decouverte weekly ticket we went to disneyland paris and the disney studios we visited the aquarium the notre dame cathedral and big galleries in spite of the rain we enjoyed just walking on the streets
17  1465150480
versaills luxembourg garden
32  1465152132
we did a series of &#39;paris walks&#39; on our own with a w-ifi gadget and explored quieter areas of paris with less touristssimply the best way to see paris with quaint streets  quieter museums and galleries the original bike tour company gave us a brilliant 3 hour tour and there was only the two of us on it! went to museum d&#39;orsey about 4:30 pm and found it less busy couldn&#39;t avoid queues at saint chappelle but stunning stain- glass windows views from dome of pantheon amazing and worth the climb
12  1465152175
timing bad as it was very coldthe sienne has overflowed hence famous museums closedalso major transport strike so could not get to versailles thank goodness our accommodation was excellent
51  1465155475
paris is paris !!!! never a dull moment
52  1465155513
no wonder it is the most visited city in the world!great history culture romantic great french foods great shopping
20  1465155946
paris is a beautiful city!expensive but beautiful
84  1465156147
do some research before you go  select targets and stick to them as you can&#39;t possibly see everythingimmerse yourself in a &quot; quartier &quot; eg the marais st germain des pres quartier latin   walk ( flaner) as much as possible only use the metro when necessary  for eating&#47;drinking steer clear of the boulevards  for a &quot;specil occasion&quot; lunch&#47;dinner use a reliable guide to the best brasseries  otherwise if a place smells good and has lots of people inside it can&#39;t be badmake a point of getting to know your &quot;quartier&quot; and discover your own places
28  1465156490
the bars and cafes of st german de pres and the latin quarter are right at your doorstep  the left bank of the seine is close and walking to notre dame  st chapelle  the musee d&#39;orsay l&#39; orangerie  and the louvre is easy  such a feast of art  we loved having chestnut crepes at place de la concorde  and watching school kids sailing toy boats in the tuilleries and le jardin de luxemboug  didn&#39;t like the musee quai branly  despite its huge collection of tribal artefacts  a lot of witchcraft  voodoo and human sacrifice  followed by a truly awful meal in the cafe  the only low point  redeemed however by the magnificent eiffel tower and trocadero next door
44  1465156885
went for french open in roland garros we  were fortunate that the rain had cleared but time for a roof to be built i know paris quite well and enjoy the city and all it has to offer you need to know the quality of the restaurants as we had a very bad experience rude woman and over priced poor food
54  1465157229
easiest city to get around and one of the most beautiful cities in the world by far
10  1465158824
the open top bus tour is recommended by us for visitors who want to see the main sights easilyparis is a very busy city both  traffic and people
41  1465161211
go for hop on hop off ticket is valid for two days so we did the most attraction through it quite informative as well
78  1465163028
all the main sights
80  1465163628
although the weather was inclement the galleries and cafes made up for this  the rodin museum is a must see
54  1465165108
had to travel by eurostar at last minute as flight was cancelled  no information points at rail station ( think due to strike) and due to flooding some main attractions were closed as were key metro and rer stations despite all of this we managed to have a good time and will arrange a second visit hopefully without floods closures and cancellations as we loved the cafe culture and spectacular buildings  will allow time for them to sort the damaged caused by river seine flooding first very expensive though!!! have now learnt that opening a tab and hinting that you are staying for a week can lead to reductions in prices of drinks so will try that next timeÃ°Å¸ÂÂÃ°Å¸ËœÆ’
46  1465165336
science museum is a great place to take kids to especially if you&#39;re staying at a hotel just opposite the museum (forest hill hotel)
20  1465165763
there is some beautiful places to visit in paris but expect it to be expensive for eating out
69  1465166851
paris one of our favorite cities  location of hotel near rue cler which is our favorite neighborhood  unfortunately weather was bad and we left the day the river went outside it&#39;s banks  we travel every two years to europe and always route our self&#39;s through paris  better weather next time
58  1465167612
easy to get around and just be ready to pay expensive lunches and dinners if you go for the local food places preferably i will go to chinese restaurants kebab places buy sandwiches to the supermarkets etc to save some money in the food like i did same if you go to disney land bring food and water the prices in disney land paris are just to high at the restaurants
40  1465169744
paris is an exciting city with so many sights see we love the many cafesthe coffeefoodand scenery   we are not particularly city people so a few days is right for us   we toured most of france loved the scenery and people   it was the best vacation
33  1465170603
all the monuments were well presented and well explained  food was ok set menus a bit boring in st michel tourist precinct not much variety
12  1465173541
the french people are great but very hard to find
48  1465175790
we traveled on the seine using the batobus to most of the famous locations which made it very easy
61  1465180982
everything!the architecture history arts food and parisian sense of style    our family fell in love with paris and we will return
0   1465182836
strolling along the seine in spring watching street dancers and other performers lovely cafes etc
67  1465183073
sooo many places to go  plan ahead and don&#39;t plan too many places on the same day  take each visits slowly to get to know all the places well  walk by foot if you can and enjoy the city!
67  1465189920
fantastic city with amazing culture and sights only in town for the weekend and crammed as much as possible    highly recommend getting a visitor pass for the metro 24 euros for a three day pass gives you unlimited travel within the central zones; was perfect for exploring the city    plan your trip well to avoid incredible queues at the major attractions that can eat into your time very quickly
93  1465192367
we visited notre dame eiffel tower and the louvre the cattacombs are also worth a visit we traveled everywhere on the metro
6   1465192452
love paris we were just passing by this time the strike with cancelled trains and the flood made everything more complicated and uncertain to get around and reaching our final destination but people were friendly and helpful
57  1465193721
like most large cities paris has problems with expensive taxis and traffic congestion so use the metro and buy a &#39;carnet ticket&#39; to save a lot of money  huge range of foods to eat and places to eat them from simple snacks to gourmet meals paris has them all learn a little french it definitely helps to get you to where you wish to be without too much hassle and smile!!!!
16  1465195378
paris is a beautiful city with amazing food just need to be aware of the driving and taxi journeys which can be a little crazy long and expensive rounded the crowded streets of central paris
40  1465195951
found many areas of paris quite intimidating due to large numbers of people hanging around on the street not doing anything but just staring at you as you walked past - my girlfriend would not walk alone in one street to gare de l&#39;est for example
92  1465195998
we love the marais district and the musee carnavalet
59  1465197307
great city
54  1465197839
we experienced very friendly and helpful people in shops and restaurants most of them speak english we struggled a bit with our 3 days metro pass because it only worked properly a couple of hours and then we had to go to information every time we wanted to use the metro no one could tell us why there is a problem and how we could solve it but overall it was fine we always managed to get on metro and in reasonable time  we had to take rer on sathurday to go outside paris there was a strike and we have been directed 2 times wrong until we managed to catch a train however we arrived in time and everything worked out fine overall we have been supported very friendly
21  1465198127
paris is just gorgeous!  the architecture the food the people      you must go to leon for mussels!
60  1465199705
we love paris as we have been there many times this time we were affected by strikes and heavy rain with flooding which took the edge of the trip we can&#39;t blame the french for their weather
56  1465200063
paris is amazing!!  people are so nice and friendly  food is superb  shopping is expensive but which touristic city isn&#39;t?   but the best part of my trip to paris was euro disney!! (you have to go regardless of your age its more than amazing and much more than just fun its the happiest most peaceful place on earth)
25  1465201459
it has lots of historical places which could be good motivation to go there however it is not very welcomed for non french speakers the food in the restaurant is non sense expensive and some of them are even low quality it was expensive trip which you could spend it in cheaper and more hospitable city
36  1465201631
i love paris this was our first 3 night stay with children in tow but still managed to enjoy it   use the hop on hop off bus and check with the discount booklets as you can usually get some good offers
33  1465201767
paris is always expensive so shop around for beat hotel deals  some hotels are very poor but charge high rates   read reviews  also be very careful with your belongings when moving  there are many pickpocket s operating
61  1465203172
quite expensive
77  1465203890
such a beautiful city wil definitely be making another trip soon
10  1465204852
i loved the architecture and the galleries
6   1465206246
great trip and time in paris weather was great and the food is to die for
91  1465206254
paris is always great rain hail or shine this time plenty of rain favourite city in the world!
37  1465206906
everything
86  1465207890
notre dame is a must but don&#39;t bother with the audio as its recorded well prior to current configuration of the church  book well in advance online for eiffel tower tickets so you miss the queues  you must go to the louvre!
10  1465209255
beautiful always eager to come back and visit more places
61  1465215050
we found paris to be rich in history but the people living there are absolutely obnoxious and rude  we had no joy with any of their services and had to bumble our way around the metro  paris needs to be more tourist friendly and should understand english - which they clearly do not
15  1465215307
ensure you check ahead of time for attractions operating times  check for peek tourist traffic and try to avoid the times with heavy traffic you can actually get to and from the airport by using the subway but give yourself lots of time  watch out for pick pockets!
42  1465215549
we loved our 4 day stay! we ate at several places several of which were on  the rue de la huchette where our superb hotel was for three mornings we had delicious reviving breakfasts at the boulangerie saint michel the evening of our arrival we had a great meal at sarl le symposium the restaurant directly opposite our hotel room we had another great meal the day before we left at the restaurant of our hotel during our stay we visited: the musee picasso the louvre notre dame pere lachaise cemetery jardin de luxembourg - twice as we liked it so much and several cafes where we sat outside and drank cold beer! on the way to the louvre we saw the daniel hourde exhibition along the pont des a&#39;rts - very interesting and of course all the locks on the pont neuf   paris is easy to get around - we tended to use the metro which was shut only once: our local station that is - st michel because of the flooding - the worst for 30 years apparently several other metro stations near the river were also closed   the only frustrating thing: any attempts to speak french were halted instantly as almost every waiter shop&#47;hotel worker and pedestrian speaks english and can tell an english accent after one syllable of (attempted) french and would occasionally display some annoyance - quite understandably - when we would continue to try and converse in their language! it&#39;s probably enough to begin the conversation by saying &#39;bonjour monsieur&#47;madame&#39; then carrying on in english that way they will respect the fact you have greeted them in french at least! this is not a criticism however mearly an observation comparing paris with london: nothing in london - not the parks museums architecture food&#47;wine - the prices and selections in the local supermarket was incredible - compares also the people - especially the waiters (all much older men than you&#39;d see in restaurants in england) are so much friendlier than in england we loved our stay and will be back as soon as we&#39;ve saved some more money!
56  1465217338
it wasn&#39;t a great time to be there (early june &#39;16)  the weather was dull and occasionally wet the seine was in flood causing museums and attractions to close (louvre and musÃƒÂ©e dÃ¢â‚¬â„¢orsay among others)  there were strikes and demonstrations so generally the rÃƒÂ©publique wasn&#39;t feeling good about itself  to top it all i met several native frenchmen who said they thought france should get out of the eu if only they had a choice!
7   1465224342
fantastic city
28  1465227589
once you know the transport system and use a travel card is it very easy to move around we visited a few places but the time was too short to see even the best places paris has to offer highlights are the dinner on a boat at night and of course the eifel towera sunday visit to province an hour and a half ftom paris was great it is easy to find restaurants and small cafes we found that the portions are big and not that expensivethe atmosphere over the weekend during the evening was incredible as people were enjoying themself&#39;s next to the river
24  1465228648
paris is a beautiful city and expensive we only had three days which is nowhere near long enough
49  1465229390
it is beautiful in paris! old buildings are properly maintained love walking around in the big and small streets metro and trains are so convenient you just need a day pass or a paris pass to include free admission to all the museums palaces and even versailles but you&#39;d better learn a little bit of french before going to paris without saying your greetings and&#47;or goodbyes in french you are dead! the french are too proud of their own language
77  1449151616
the security is not safe otherwise is good just need to be careful underground pocket picker
32  1449153180
the atmosphere
47  1449155420
the greatest city on the planet but in small doses
76  1449155967
i liked eiffel tower is at its best museums shopping some people are really warm and some irritate you a lot wish they understand every person comes to france cannot speak french and respect other country citizens    few were super cool spacious roads and superb life impressed me
41  1449161461
the metro is very easy to use and the rer although the ticketing system can be confusing    i had dinner at la loraine place des ternes which was very good    traffic awful
84  1449161936
the architecture - the ambiance - the romance - the wine - the river - the boats - the art - the sculpture - the food - the churches - the streets - the cafes - the coffee- the people - sit down and rest a little as the world walks by - dream a little
8   1449163128
lots of people coming out after the terrible events of two weeks before queues were shorter than normal at tourist spots
10  1449171565
very good public  transportation best to use the metro or bus with the daily tickets  get to know paris best way is to join the free paris tourfind it up in google young parisians will introduce  you the city in a unique  way every day we took another tour in another neighborhood   take a cooking class or wine &amp; cheese  pairing lesson at &quot;the foodiest&quot;cook school
46  1449175463
one of the worlds great cities
69  1449189411
the atmosphere food history attractions the lifestyle
48  1449211263
take the subway and train to go any where  visit  small eatery (restaurant ) for better food   buy beer and wine in the supermarket lots of choice and cheap in price
15  1449216745
paris is great one tip - don&#39;t take a wrong person with you but even if you make this mistake - like i did - it wouldn&#39;t ruin paris for you everything else is epic there
12  1449220722
pantheon cathedrale notre dame de paris centre pompidou atelier brancusi tour eiffel avenue de champs elysees dome des invalides musee de l&#39;armee  you can eat very tasty french pizza at flam&#39;s colisee
36  1449230117
beautiful country with lovely food  good and cheap transportation
50  1449233962
the public transport (especially the subway network) is great in paris suggested to eat in the local small restaurants and bistros worth visiting the markets: beautiful products friendly people
57  1449250264
i loved all the sites and how the transport was free for the 2 days i was there
3   1449305619
ate everything favourite place was restaurant next to eiffel tower fav place would have to be the louvre massive was a experience we found it easy to get around paris was explained to us very easy an we took it from there erm there&#39;s nothing would avoid about paris didn&#39;t visit as much places as would like to have but overall was very happy!
43  1449326763
a day pass on the metro allowed us to travel all over up to montmartre then to la defense  the christmas market at la defense was amazing!then eiffel tower les invalides la bastille and some iconic stations
33  1449327459
the places we liked the most were eiffel tower louvre and other tourist attractions the architecture and beauty of the place best moments were spent walking around the streets of paris we tried the local cafes and loved the wine in france it is easy to get around by walking cabs or using the metro&#47;bus people were helpful and polite please avoid falling prey to dodgy taxi scams only use government regulated taxis with signs on them
73  1449327840
paris is unfriendly to handicaps and people using baby strollers you have to go up and down in each and every metro station and no lifts or escalators    we were robed in the first hour we reached paris and found out that this is a day-to-day practice police was not that helpful    i will never go back to paris it is an overrated city
30  1449328319
everyone was amazingly friendly and welcoming we loved the louis vuitton foundation - amazing building and art installations versailles gardens (free) were vast beautiful and hugely impressive with endless vistas and extrordinary perspectives every single thing we ate was delicious  try dessance in le marais and relais de venise at port maillot  all very easy to get around (metro and rer) but we walked mainly as it&#39;s flat loved the whole lot
15  1449349087
i can&#39;t pick any highlights - i literally smiled from arrival until i had to leave! travel was so easy either by walking or metro! do it all - definately recommend a river cruise and make sure you got to the top of the eiffel tower metro paris visit pass is so handy and can be purchased online in advance along with fast track entrance tickets - these are great if you are only visiting for the weekend and dont want to waste time standing in queues! try to see lots of landmarks at dark as well as during the day - they are stunning!
81  1449350243
the architecture is beautifulthe city has got a special atmospheregreat for shopping
6   1449356601
palace of versailles was great ate close to hotel cafes cuisine amazing very easy getting around metro great &amp; very clean
26  1449361744
the architecture and parisians are actually friendlyits an open city with different natinalities
47  1449370382
architecture is stunning tower restaurant at night incredible louvre is just the be all and end all of art galleries metro can be confusing - have to understand there are two railway networks embarrassing when tickets don&#39;t open gates no easy remedy apart from climbing through the gates     beware the romany scammers attractive young girls getting you to sign a petition and make a donation huge scam and very well organised    food glorious everywhere!
84  1449377551
paris is magical we have visited at least 5 times no matter how long we stay it is never enough time to see all the magical sights!  take the metro to bastille and the canal boat on the canal st martin - an absolute highlight through all the little locks!  the louvre of course encroyable!  the left bank always magical  if you love monet - galerie marmottan-monet near the bois de boulogne - take metro muette - a charming area  visit fauchon in place madeleine even if you only window shop - it is magical!  visit harry&#39;s bar  at 5 rue danou for a superb cocktail and an entertaining and incredibly barman!  spend time in rue des rosiers the oldes jewish quarter in paris look up and see the plaques on the doors where the jews were turned in and deported to auschwitz a terribly sad part of history  walk in the beautiful place des vosges and have a little glass of rose in a bar in one of the beautiful arcades surrounding the place  walk over the bridges on the seine at night - magical!  paris is full of endless beautiful things to do - just get out there and enjoy it!!
26  1449393139
the city us unique with a lot of beautiful buildings and rich in history must try to eat all the local french cuisine although some of it has acquired taste but it&#39;s really worth the try! i love shopping in decathlon (sport store) after being introduced by a local parisian as it offer very good quality sport items with a very cheap price yes really cheap price!!!
36  1449402543
many things to see whole of paris is super
59  1449404839
our favourite places are arc de triomphenotre damelouvreseine river cruisesacre-coeur basilicaeiffel towerwalk down champ elysees  we eat in restaurantsfast food and street food too  it&#39;s easy to go around paris by metro but its very important to be very careful of pick pockets in the train and on the way to go into the train  we would like to avoid taking trains if we shops a lot in our next visit
14  1449412725
yes very easy to get around paris
59  1449433822
we stayed right in the heart of montmartre and i would recommend this as a base for travelling throughout paris city of love and light - just walk take a metro walk take a coffee walk
74  1449438045
beautiful city wonderful sights culture eating out shopping and travelling around all excellent
86  1449439110
generally very exciting and great city ate in different restaurants enjoyed bhuddabar most  very easy to get around either by taxi or train parisians are great people  i would avoid staying at my flat in paris
34  1449459978
its a happening city it architecture and also means of transport within the city is very good
34  1449479762
paris has lost some of its glamour no longer on a par with other major european city over priced and lacking in atmosphere
87  1449482087
everything
91  1449488378
try and walk wherever possible you&#39;ll get a real flavour of the city musee d&#39;orsay and the pantheon were my favourite spots     paris is an incredible city with so much to see that you will need to plan ahead!     at gare du nord do not take a taxi from anyone other than official vendors a smartly dressed man offered us a taxi and charged 120 euros for a short trip! 15 euros in an official cab!
66  1449500726
great food and wine on any given street corner beautiful monuments representing the finest human achievements excellent public transportation system and very friendly!
76  1449502115
friendly and vibrant city not enough time to explore all hope to come back soon
27  1449519820
we visited paris third time and this time we were going to walk around the famous paris sights we are pleased with the city itself people christmas markets and cafes it was an amazing trip  in a lively town
89  1449524354
sad and quite due to the bad circumstances that occurred
39  1449531868
getting around was easy just need to be vigilant about pickpockets roaming around the metro or streets service staff (waiters store assistants etc) are not all superbly friendly to us some even shoved menus in our face (literally)
88  1449561614
i like the beautiful architectural history food and weather
34  1449571874
air france buses are great if they run to your preferred locations great food at le petit champerret i prefer less touristic areas because i have been in paris so many times great new shopping center at levallois souest
36  1449573152
paris is a beautiful vibrant romantic city so much to see and do - it even brought the romance out in my other half even venice didnt do that !!
60  1449580154
plenty of good reasonably priced restaurants lots of attractions to suit every taste and budget
46  1449580440
i&#39;ve been to paris many many times - it&#39;s my favorite city and i love it every time  so many wonderful interesting things to do and see - it&#39;s endless!  the only thing i do not like and luckily i do not encounter this every single time but this time i did: very rude waiters in one or two places!  i guess some things never change but luckily the service was good in most other places but even the bad service here and there will never change the way i feel about paris  :-)
22  1449583721
Ã¥Â¤Å“Ã¦â„¢Å¡Ã§Å¡â€žÃ¥Å¸Æ’Ã¥Â¼â€”Ã¥Â°â€Ã©â€œÂÃ¥Â¡â€Ã©ÂÅ¾Ã¥Â¸Â¸Ã¥â‚¬Â¼Ã¥Â¾â€”Ã¥Å½Â» Ã¥Ë†Â°Ã¥Â·Â´Ã©Â»Å½Ã¤Â¸â‚¬Ã¥Â®Å¡Ã¨Â¦ÂÃ¥ÂÆ’Ã©Â¹â€¦Ã¨â€šÂ Ã¨Â¶â€¦Ã¨ÂµÅ¾ Ã¤Â½â€ Ã¥ÂÂÃ¥Å“Â°Ã©â€œÂÃ¤Â¸â‚¬Ã¥Â®Å¡Ã¨Â¦ÂÃ¦Â³Â¨Ã¦â€žÂÃ¥Â°ÂÃ¥ÂÂ· Ã¥Â·Â´Ã©Â»Å½Ã¤ÂºÂºÃ¤Â¸ÂÃ¦ËœÂ¯Ã¥Â¾Ë†Ã§Æ’Â­Ã¦Æ’â€¦ Ã¤Â¸ÂÃ¥Â¤Å¸Ã¥Ââ€¹Ã¥â€“â€ž Ã¨Â¿ËœÃ¦ËœÂ¯Ã¦â€ºÂ´Ã¥â€“Å“Ã¦Â¬Â¢Ã¨â€¹Â±Ã¥â€ºÂ½Ã¤ÂºÂºÃ¤Â¸â‚¬Ã¤Âºâ€º Ã¥Â·Â´Ã©Â»Å½Ã¦Â¯â€Ã¨ÂµÂ·Ã¤Â¼Â¦Ã¦â€¢Â¦Ã¥ÂÂ«Ã§â€Å¸Ã¨Â¿ËœÃ¦ËœÂ¯Ã¦Å“â€°Ã¤Âºâ€ºÃ¥Â·Â® Ã¨Â·Å¸Ã¦Æ’Â³Ã¨Â±Â¡Ã¤Â¸Â­Ã§Å¡â€žÃ¥Â·Â´Ã©Â»Å½Ã¤Â¸ÂÃ¤Â¸â‚¬Ã¦Â Â· Ã¤Â½â€ Ã¥â€™Å’Ã¦Å“â€¹Ã¥Ââ€¹Ã¤Â¸â‚¬Ã¨ÂµÂ·Ã¥Å½Â»Ã¨Â¿ËœÃ¦ËœÂ¯Ã¥Â¾Ë†Ã¥Â¼â‚¬Ã¥Â¿Æ’Ã§Å¡â€ž Ã¥Âºâ€Ã¤Âºâ€ Ã©â€šÂ£Ã¥ÂÂ¥Ã¨Â¯ÂÃ¥Å½Â»Ã¥â€œÂªÃ©â€¡Å’Ã¤Â¸ÂÃ©â€¡ÂÃ¨Â¦Â Ã©â€¡ÂÃ¨Â¦ÂÃ§Å¡â€žÃ¦ËœÂ¯Ã¥â€™Å’Ã¨Â°ÂÃ¤Â¸â‚¬Ã¨ÂµÂ·Ã¥Å½Â»
84  1449584436
you cannot not go!
84  1449584599
despite the attacks i still feel very safe in paris they are checking everyone before they enter a big store theatre or circus  if you visit between the end of october and the beginning of march be sure to get tickets for the cirque d&#39;hiver-bouglione near place de la republique in front of the metro filles du calvaire it is one of the best entertainment venues paris has to offer big live orchestra a ballet that can easily be compared with the lido or moulin rouge and a wonderful deluxe atmosphere of the oldest surviving circus building in the world  also go and see the mÃƒÂ©nagerie in the jardin des plantes it is one of the world&#39;s oldest zoos and great for kids and not too big
88  1449584851
i&#39;ve been to paris four times now and each time i&#39;ve stayed in a different part of the city this time was the latin quarter and i was impressed    great places to eat affordable and tourist friendly
65  1449585558
eiffel tower a must book online at home though avoid queuesmassive christmas market on champs elysees
70  1449587315
great food go back a few streets from the main attractions it&#39;s cheaper i buy a paris visite card at the airport it covers all my travel during my stay &amp; gives discounts in places i went on a river lunch cruise food was a bit fancy but delicious
24  1449588387
paris is fantastic if you can&#39;t walk it the metro is so convenient (and cheap buy a carnet) we found a great restaurant les pres de clerc which is reasonable with a great atmosphere &amp; has a varied menu can&#39;t wait to go back!
62  1449591214
magic
33  1449594436
went to all the famous locations all transport was quick and easy to use went out to eat at anywhere that looked nice would recommend gustaves
25  1449597628
the beauty of the city of love  the louvre the eiffel tower and the christmas market
68  1449608785
so many things to do for everybody&#39;s taste requiring a little research for anyone visiting this wonderful city  one week is too little time
41  1449608911
i love the culture - the opera the ballet the music the art  i also love the history that permeates all the beautiful buildings and boulevards  paris is stylish elegant romantic justifiably snobbish and exorbitantly expensive  high points are the louvre (of course) sainte chapelle the picasso museum the rodin museum l&#39;ardoise for great inexpensive food palais garnier for a fabulous ballet experience opera bastille for world-class performances rue st honore for highly elegant shops laduree for its divine hot chocolate and galeries lafayette for department store heaven    paris is atmospheric nostalgic sometimes sad (especially at the moment - 3 weeks after the massacres) yet completely life-enhancing  everyone should go there at least once in their life
15  1449654099
i was visiting family so the trip was personal to me but to be there at this tie of year was lovely the champ elysees was magical with the beautiful christmas lights the christmas market was also delightful  everyone was in good spirits and the feel of the place was warm welcoming and happy
12  1449656191
wandering the streets sitting under the eiffel tower lit up at night visiting the lourve walking along the seine the city itself is just so beautiful!the metro makes it super easy to get around and then once your in a certain area i found it very easy to navigate the streets using maps or my phone
47  1449657757
very friendly good transport system
14  1449660494
visit fondation cartier  visit fondation louis vuitton  visit small musea like zadkine delacroix dapper  visit l&#39;egout de paris (sewer museum)  visit jacquemart-andrÃƒÂ©  visit a lot of brasseries along side the boulevards
36  1449663217
favorite place was sacre coeur basilica
99  1449664058
the monuments the restaurants the people
22  1449668412
paris is now more tensed and much less crowded  but still  it is beautiful  we had a great time
29  1449668508
i was there for a business meeting and to attend an exhibition    we ate in the brasserie pierre about 50 metres from the hotel excellent food great atmosphere reasonable prices    the little bar next to the hotel was clean and good value
61  1449681510
eiffel tower restaurants shopping
91  1449689857
paris is a city with many elegant buildings and monuments and a great city for art lovers gourmets and nightlife try the eiffel tower at sunset with champagne at the top or the picasso exhibition in the grand palais - pre-booking is advised for both one downside is that it is expensive
62  1449751772
roissy bus very convenient  to central city    christmas decorations magnificent especially printemps and galleries lafayette  lots of lovely shops in little alleyways   less crowded friendlier citizens good service    lots more security entering shops  and restaurants and airport lines long   great weather   metro easy to use with signposts &#47; maps everywhere
36  1449757358
i go to paris to catch some great music so in 3 days got to see 2 of my favourite operas at the opera garnier and went to 2 great small jazz clubs other favourites include the musee d&#39;orsay the orangerie and walking walking walking! easy transport just about everywhere and very reasonable places to eat
71  1449758290
to sum paris in a few words  africans  romanians  beggars  thieves     i hated the amount of (mainly africans) who are trying to sell you things or pick your pocket i&#39;m a copper so i&#39;m a bit more clued up to things and can get out of situations however i still nearly got into a scrap with one africanthey try and grab your hand and talk to you whilst their friend goes into your pocket     i went to all the tourist places tower louvre lafayette grand palais blah blah blahall i can really say is watch your back!   i fear for people who are not clued up and a bit naive to the modern world  basilica was the worst so many beggars    keep your hand in your pocket (so they can&#39;t grab it) don&#39;t look at any of them and if they (which they will) try and speak to you pretend that you are russian or something they all speak french english and spanish fluentlygive them a reason not to talk to you
77  1449763512
i am a complete history geek so: louvre musee d&#39;orsay orangerie grand palaise sacre coeur garnier opera house versaillesi could keep going! getting around is so easy with the metro tip buy the book of ten tickets for the metro it&#39;s much cheepersplit with a friend if you don&#39;t think you&#39;ll use them all! also if you use one ticket you can use it again on a different metro line in the same hour ie: travell on metro to sacre coeur and then use same metro ticket for the monorail to take you up the hill to the church! if you&#39;re staying centraly you can walk to alot of things the christmas village on the champes elysees is a must see if you go in winter going up the eiffel tower is great but pick your day as an overcast day will really effect the view and photos the galleries lafayette is well worth a visit even if your not a shopping addict this city is so easy to get to and has something to intrerest everyone i have been twice now and will definately be going back!
65  1449770969
there is so much to look and marvel at paris is a tribute to the highest of human achievement in architecture it is as grand as it gets i felt super safe walking around paris on my own at night strolling is most perfect at night with the lights of the city twinking paris is a rare gem
86  1449777508
people in paris are helpful but they are unable to speak well in english and thus for chinese like us may face a problem further more the smokers were not considerate at all they will just light cigarette and smoke without considerate non-smoker who are nearbyworst still is in disneyland which most of the kids will suffer
49  1449779580
paris near the siene river is good where i stayed and north of gare le nord was quite seedy with many people trying to trick you to get money  out of the countries i went to in this trip this was only place felt a bit uncomfortable at times i left paris the day the terroris attack occured
59  1449779715
love paris!  the people the architecture the history are what i adore exploring luxembourg gardens marvelling at the louvre and notre dame and visiting versailles are personal highlights  i use the metro to get around easily and because it is relatively cheap i have a little euro for a sucre and citrus pancake!
71  1449779869
it&#39;s not called the city of light for nothing absolutely beautiful city with loads of open spaces the people are incredibly stylish and just watching what they were is an experience in itself also for the large part the people were really nice and helpful - few exceptions as in any other place in the world metro&#47;sub-way system is second to none - loads of stops and lines and never waited longer than 5 minutes for a train to come along some trains are a bit old though - very minor complaint    montreau area was a unexpected surprise which we stumbled upon and was really nice la fayette and champs elysees is were the shopping action happens in terms of sights sacre coer for its views of the city notre dame for its beautiful architecture arc de triomphe for its history eiffel tower for its iconicness (?) louvre for its sheer size and beauty (don&#39;t miss napoleon&#39;s apartments) and versailles (especially the gardens) wow just wow    the food is great and the pastries are like nothing you&#39;ll ever taste in your life
92  1449784083
we traveled in december so christmas fairs were great  the bets places in paris are (from top):  - eifel tower - you can see it from any place in the city;  - notre dame;  - champs elisee;  - mon matre;  - louvre  recommendations from booking where to eat were good enough + we visited 2 moulens cafe where amelie worked in a film  you can find a lot of rare supermarkets near metro stations  metro in paris is strange: comfortable but no so clear) it works even during the night with 5 - 7 minutes trains interval travel from-to airport is really good  main problem in paris - huge queues in all sightseens we were able to visit eifel tower only at 22:00 in all other places you should wait in q queue few hours at least  be carefull - sunday almost all shops are closed and during workdays - they start to work from 10-11 and closed at 20:00
77  1449789536
incredible sights and a very user friendly and thorough metro system
60  1449797292
this was a thoroughly enjoyable trip as an old school friends reunion thus just a short stay this time so easy to get there on eurostar and you arrive in the city itself we walked or used the buses which are very cheap we were three ladies and at no time even at night when going out to eat felt uncomfortable or threatenedpeople were very friendly and helpful we prefer to eat local rather than in &#39;touristy&#39; places and found very nice cafes and bistros to eat with varied and well priced choices chez claude near the louvre is excellent especially for breakfast with the best fresh squeezed orange juice! we love the musee d&#39;orsay for the building as well as the exhibitions a walk by the seine at night is a must and we really enjoyed our night walk up the champs elysees to visit the christmas market the atmosphere was just a little subdued due to recent events but this in no way detracted from our trip all main sights are close together and it is an art lovers paradise the food and wine is good too!!!!!!!!!
1   1449807415
two u2 concerts were great Ã¢â€šÂ¬10 for a pint was a shock !!
87  1449822665
it is easy to get around paris  i took hop in hop out package to tour around paris  lot of places to visit but not enough time (1 day) most notable place is effiel tower
26  1449823364
beautiful place! warm and friendly people! great food! most amazing places to visit!
33  1449823773
paris is amazing beauty i like it and want to come there again
37  1449825437
absolutely travel around streets don&#39;t just use main roads feel the real paris use metro it&#39;s cheap and connection is very easy visit latin quarter and shakespeare &amp; company book store louvre and almost all the museums are free if you are under 26 and european union citizen
82  1449830538
stunning christmas market at the champs elysees
72  1449831363
musee d&#39;orsay eiffel tower and notre dame   bastille district is cool for dinners and have some drinks with friends
41  1449832329
everything is great in paris
13  1449833156
eiffel tower is great especially one hour before the sunset  montmartre is good to see by night
28  1449835941
the visit to the eiffel tower and the river cruise was great    some great eating places around ntre dame    everybody was very helpful
75  1449841112
best region monmarta
78  1449853431
the christmas market was great
86  1449859130
i love everything about paristhe weatherthe vibethe buildingthe peoplethe foodbut my suggest avoid metro around 9pm its too scary and confusing and dark especially alone  over all i will back for more
94  1449861802
stayed in paris for 3 nights had a great time would defiantly go back
98  1449862764
wonderful views from eiffel tower in the evening and from sacre coeur basilica during the day champs - elysees at night
97  1449867049
st louis latin quarter   walk in thuilleries gardens
2   1449870191
paris is reallyreally awesome if you like history stonework art shopping( &#39;window&#39; less costly ) for anything walking relaxing  etc!  there is a certain &#39;je ne sait quois&#39; as they say;)i was there a week before the terrorist chaos and again 3 weeks later  i have great respect for their spirit &amp; attitude to return to life &quot;as normal&quot; it&#39;s my 3rd time therefirst 3 days in 2014 then again for 6 and 3 and will likely always make it a landing or departing spot to enter europe!!  truly awesome easy to navigate especially walkable if you like that  or metro if you don&#39;t felt very safe there even alone at night and walked almost everywhere up to 11 pm   heightened security meant having security ask to look in your bag when entering a store or historic building&#47;monument and no big deal   paris is much friendlier than i&#39;ve read and found most people willing to try and communicatesaying bonjour immediately in a store helpsit&#39;s polite and easy enough :)  i discovered things i&#39;d read about walking and admiring the neighborhood(s)missed out going&#39;up&#39; all those stairs at notre dame &amp; arc de triomph end of the day but gotta save something for next time :) figure 5 day minimum every time maybe longer ;)  patisseries &amp; bread from heaven charcuterie &amp; cheese from there too good wine &amp; atmosphere x100!!  can&#39;t beat it it oozes charm and has a very interesting feel  classy yet edgy sophisticated yet gritty and definitely rebellious and resillientstrong and confident!!yayyyyy paris and all the people in it!  rented an apartment so i didn&#39;t have to eat restaurants always and found that an awesome way to relax at nightwine &amp; cheese plate with  a view and feet up!  so far my favorite big city from : berlin munich vienna istanbul milan amsterdam dresden zurich berne geneva
59  1449885708
being on the seine in sight of notre dame close to the latin quarter boulevards st michel and  st germaine the  hidden gem in the church of st severin this area is superb full of cafe&#39;s shops good restaurants and the st michel metro station i cannot think of a better area to stay when in paris    a metro and museum pass are mandatory as is a 48 hours bus pass on the big bus for those who wish to have more &quot;drop of points&quot; the foxicity could be your choice big bus included a 1 hour boat trip on the seine    getting round paris is much easier than london and hongkong where we went after paris    we only had 4 nights and i recommend 6-10 nights to allow visiting places like versailles (i whole day) and monet&#39;s garden at giverny (also 1 day)
78  1449901751
musÃƒÂ©e d&#39;orsay louvre orangerieand many moresimply walking in paris is merveilleuxalso visiting the new fondation louis vuitton at the bois de boulogne was a great experience
75  1449903234
(louvre champs-elysees and eifel tower) must see food only burgers and italian restaurants worth the money french meat is worst than bulgary&#39;s tooo expensive water
9   1449911026
effel tour  lebanese food  shopping
44  1449911624
buy a carnet of 10 tickets for your tube travel it makes things easier you can also use these tickets to go up the funicular at mont matre
63  1449912358
the hotel doisy etoille!amazing location fabulous pleasant staff great church bakery restaurant and market nearby
74  1449914964
get hop on hop off  bus great way to see paris
78  1449917764
best thing about paris is the walk along champ elysee it was really pretty with the lights lit up at night     the only thing i didn&#39;t like was that my husband was nearly robbed at the train station! Ã°Å¸Ëœâ€“
98  1449917779
the public transportaion system is very good it hardly takes few minutes to get the bus or  train metro from city i couldn&#39;t get good indian food over there
45  1449922304
it&#39;s an intimate city  you can walk everywhere  musee d&#39;orsay and boat trip are a must do  book eifel tower visit in advance  we like cafe madeline
31  1449925129
eiffel tower  place de la concorde   tuliers gardens   monmarte is the lovely place in paris
74  1449929153
architecture was awesome
73  1449930111
paris is an astonishing city i have visited several times and know the major sites this time it was looking stunning in winter sunlight and not too many tourists the buildings shone golden and clean although the streets are not quite as free from dog mess as one would like we were meeting  various people and also pursuing our own detailed explorations it is easy to choose somewhere to eat well and reasonably easy to get around by metro or on foot there is nothing i would avoid and loads more to see
28  1449930202
ÃÂ¿ÃÂ°Ã‘â‚¬ÃÂ¸ÃÂ¶ Ã¢â‚¬â€ Ã‘ÂÃ‘â€šÃÂ¾ ÃÂ¿Ã‘â‚¬ÃÂµÃÂºÃ‘â‚¬ÃÂ°Ã‘ÂÃÂ½ÃÂ¾! Ã‘ÂÃ‘â€šÃÂ¾ ÃÂ¼ÃÂ¾ÃÂ¶ÃÂ½ÃÂ¾ ÃÂ¿ÃÂ¾ÃÂ²Ã‘â€šÃÂ¾Ã‘â‚¬Ã‘ÂÃ‘â€šÃ‘Å’ Ã‘ÂÃÂ½ÃÂ¾ÃÂ²ÃÂ° ÃÂ¸ Ã‘ÂÃÂ½ÃÂ¾ÃÂ²ÃÂ°
17  1449930519
paris is magical if you fall in love with it the first time you are there you will never stop loving the city   and the city will welcome you back every time you go
12  1449931059
metro was easy to use and quick i enjoyed fish restaurants i visited eiffel tower and mont martre december nice time to visit weather  was mild not so many visitors
35  1449938778
i always enjoy my time in paris - this is the 2nd visit this year  st germain was an excellent location because of the walk to the seine and musee d&#39;orsay  metro station nearby so easy access around paris
92  1449939553
love the architecture and so many things to see the napoleon tomb is worth to see so beautiful!  the food is superb   they have the best coffee!
17  1449942106
ravishingly beautiful just to walk around still lots of nice local colour small cafes and bistros with friendly staff  people are being courageous and still very welcoming after the awful events of the terrorist attacks - maybe a little warmer towards visitors than before!  great shopping too if you go for elegance - we love bon marche - which also has the most amazing food store!!
48  1449945166
all fascinating  must return with more time
93  1449947543
met up with a friend traveling on the eurostar from london whilst i came in on a train from milan we rented a car and made our way up to normandy so we were not in paris very long we took the metro to the eiffel tower but the direct route was closed so we ended up getting a taxi who was really friendly and proceeded to point out all the attractions everyone i encountered on this trip from hotel staff waiters train porters car rental staff to french police on the train were friendly and helpful: they seemed to be going the extra mile - supplying great customer service  merci paris!
48  1449953612
we planned to have 3 nights in paris on our way to venice - we have visited many times before otherwise it wouldn&#39;t have been long enough this time we visited the louvre for the first time and managed to see about one third of it
74  1449964862
going to two irish pubsmaking drinking and eating simpler but other french staff were chattingto ask us to enter they had no problemspeaking english etc  relaxing timeno problemsconsidering the events in paris
88  1449979476
paris can be expensive but if you shop around you can find cheap food and drinks the subway is very easy to use and most people are very friendly the pastries are to die for!!!
6   1449992192
the weekend was just perfect we walked for miles discovering this beautiful city cafes bistros boulangeries - endless!it really is the most romantic place in the world  enjoy a river cruise taking in all of the famous landmarks walk the champs-elysees climb the eiffel tower so much to do - this vibrant city comes alive at night
63  1450001757
madonna
62  1450002221
i visited all main tourist attractive and good enough to say i know paris pretty well as tourist
21  1450003439
eifel  tower  sajeliz museum
10  1450005730
eiffel tower
50  1450006612
it is for sure the first address for sightseeing in europe even after many stays there you discover something interesting and new  you can spend many days in the museums  visiting exhibitions which are never boring  trying different restaurants or just strolling through the most charming city in the world
77  1450007988
be careful of camera theaves
23  1450008562
add extra time if you can to visit ! 1 more day can be inexpensive and worth it so you can see more and be more relaxed great city
88  1450008731
thoroughly enjoyed our four days in paris i loved going to the invalid&#39;s museum and was very lucky to be at  the sacrÃƒÂ©-cÃ…â€œur basilica whilst a service was on paris is our favourite city there is always somewhere interesting to see and do
8   1450011118
it&#39;s nice to be in paris in december
14  1450011332
it is extremely easy to get around paris by metro   there is an abundance of sights to see and visit and the cafÃƒÂ©s we ate at served delicious food  i would avoid going to see the moulin rouge by metro and recommend going by tour bus to see it instead so that you can stay on the bus and get your picture of it that way
77  1450023778
champs elysees street was the best thing in paris as usual you can enjoy waking shopping having a meal in a restaurant or even having something to drink in a coffee shop
55  1450025817
we arranged to attend wine tasting at o chateau which was very good fun and informative
8   1450028386
went to the main attractions which were within walking distance it&#39;s very easy to get around there is a metro which is easy accessible food is a little expensive where we are wasn&#39;t fantastic but the service was excellent
65  1450028661
the parisians are trying to get back to their normal lives after the nov 13 attack they deserve the world&#39;s support and solidarity to get back to their former glory
53  1450031361
paris has an amazing character we had the trip planned in advance we went to the normal touristy places and we walked most of the time i loved the fact that we had plenty of options to have decent sit-down dinner after mid-night on champs-ÃƒÂ©lysÃƒÂ©es we visited the louvre sacre-cour arc de triomphe palaise garnier lafayette notre dame saint chapel and eiffel tower   we tried various places to eat and we walked around the various monuments it is so easy to get around and you can walk to most places once you are in a particular area  the local bakeries are quite nice and very affordable i would say the latin quarters (5th arr) would be the best option for trying out various cuisines in fact i might stay in that area next time i visit paris all in all an awesome trip
68  1450031841
my favourite place in paris is absolutely eiffel tower it&#39;s even more beautiful in the evening when the lights on
77  1450032484
what not to like about paris??????  very dumb question!
59  1450032822
eiffel tower was in easy reach of our accomadation people were friendly
38  1450033356
the public transport system is very well organized and reliable! high visibility of police and other law enforcement agencies is very affective on crime!
0   1450042308
paris is paris the most beautiful city in the world!  gay vibrant fantastic museums and galleries and a pleasure to explore on foot from notre dame you must go to ile saint louis (grab a map) behind the cathedral and if in summer have an icecream at berthier on the main street  but you must beware wherever you go of pickpockets  they are active in all tourist areas  keep your belongings safe!!!  have fun and enjoy the &quot;city of lights&quot;
49  1450043227
le marais is a good walk to shop and eat     go to versailles to tour the palace take a train there from paris via rer c
12  1450044649
go to &quot;le relaid de entrecÃƒÂ´te&quot; in st germaine also stay in the holmes grandes hotel
82  1450044932
all paris
56  1450046160
super foodbeautiful sightsnice people very friendly and helpfullido show is a mustchristmas markets were amazing
4   1450066808
an amazing and beautiful city with a different experience every time you visit
68  1450073020
paris was beautiful moreso at night  the museums and churches were all incredible and the eiffel tower was magnificent most especially with the view of paris at the summit although english is not widely spoken and it was at first difficult to communicate we found some very helpful locals from the cab drivers to the store owners and to the dining staff the weather in early december was also just perfect!
93  1450073543
it is so romantic clean  alot of activities to go for and most of all parisians are really nice people i must say
57  1450073908
for a novice it can be a problem to get around paris most suitable way to travel is train  at the airport you use the free airport shuttle to move between terminals  from the airport you get rer trains to get into the central part of paris costs 10Ã¢â€šÂ¬ and in city you get metro trains to travel within city tickets are dispensed at machines and cost 180Ã¢â€šÂ¬ for each trip plenty of guidance and maps are available at stations get a map from your hotel and go wherever you like english is not well spoken but enough to find your way around louvre museum at the river bank  tickets cost 15Ã¢â€šÂ¬  typical meal will cost you around 8Ã¢â€šÂ¬
98  1450075687
visit eiffel tower using big bus tour service
68  1450076010
because it was too short i don&#39;t have anything to say
67  1450081107
louvre museum monmarte &amp; sacre coeur notre dame and walk around at saint michel walk on champs elysees and many other places    the whle city is a museum &amp; a tourist place to walk have a lunch&#47; dinner or a drink romanitc place with choices for everyone
66  1450081515
i went to paris for business a few days after the terrorist attacks given that and the fact that many other tourists and business travelers had cancelled their trips you&#39;d have thought that parisians running the hotels and other tourist and public venues would have been more welcoming that was not that case in general though there were a few exceptions paris is not a friendly place
54  1450081744
go up the arc de triomphe rather than the eiffel tower - it&#39;s cheaper less crowded and has fantastic views le marais district is wonderful for exploration; full of excellent bistros and galleries and artisan shops where you can see the jewellers etc at work the metro system is incredibly cheap (buy a carnet of 10 tickets for 14 euros: each ticket will take you on a single journey but it can be as long as you like) go on foot though when you can because you can discover wonderful architecture that you didn&#39;t know was there - and remember to look up! the parisians really appreciate you making an effort to speak in french so give it a go
88  1450094953
i went a couple of days after the terrorist tragedy to show support and solidarity my main impression was how nice the parisiens were and the muslims in particular; grateful for my using their shops saying &#39;bonjour!&#39; for no reason other than to make contact their hospitality and generosity and their collective horror at the whole episode as for paris i walked and walked on average 15km a day and there was never a dull moment i&#39;ve travelled widely during my long life and paris is up there in the top five destinations metro cheap n easy! no such thing as bad food though most of my dining out was with a cordon bleu sandwich while walking! what to avoid? getting into a conversation with the many beggars (usually female) pretending to need assistance to fill in something on their clipboard which turns into aggressive demands for money pay no attention to those who say parisiens are aloof and arrogant; that&#39;s very rarely been my experience but i do speak french which helps! just learn a few words to show respect and you&#39;ll be fine bon voyage!
83  1450098641
if you get lost easily then i highly recommend a hop on hop off tour they take you to all the major places and you have the option to stay on the bus or go explore
69  1450100902
i was here for work - night life is good and the food was excellent  ditto shopping although the extra security measures at the moment remind you how close to the edge the city is
56  1450109873
paris always delivers at any time of year spring summer autumn or winter hotels to suit any budget and more restaurants per square kilometre to satisfy the hungry traveller lunchtime and prix fixes menus keep costs low and don&#39;t cut back on quality  travel on the metro is straightforward and  cheaper when a carnet of ten tickets is bought and can be used on the next visit don&#39;t forget the extensive bus system either which gives a tour of paris for the same price useful when feet are tired from walking  expect museums to be busy and crowded some offer late opening on thursdays which can be just as busy but can be useful  my advice is take your time and don&#39;t do too much plan ahead and book museums and restaurants on line prior to visit do your homework and you will find it pays off
46  1450110088
you need at least one week to visit with enough time every place and enjoy everything not only the main attractions but the surroundings versailles is a wonderful city full of places to go not only the museum and the gardens two days at least and that is the same for everything the surroundings are important but time is needed i would say that paris and surroundings need at least two weeks to have the complete flavor of it
62  1450112246
romantic city  lovely little bistrots
86  1450118589
love the different arrondissements and the various neighbourhoods  really one of the best city in the world
96  1450119599
parc monceau
25  1450119785
i wish we had more than a weekendthe city is lovely the only thing that i was negatively surprised about was the ammount of traffic
3   1450120813
we were there when the terrorist attacks took place so no further comment
43  1450126136
notre dam was just around the corner from our hotelit&#39;s an incredible buildingwe also went to the champs eleysee was very worried due to the terror attacks but there was lots of security so felt very safe
59  1450156858
the view and architecture was amazing and impressive
81  1450172186
versailles  eifel tour  sacre cour  metro is easy and cheap to use
56  1450175451
there&#39;s nothing i can say that people don&#39;t already know it&#39;s an amazing city full of things to do and places to see    i would recommend skipping going up the eiffle tower instead do the towers of notre-dame and sacre couer     highly recommend going to the terrace on the roof of last fayette some great views and if you&#39;re in musee d&#39;orsay definitely pop out onto the roof again some amazing views
88  1450176746
i like to much this beautiful city my trip was short but i had the time to visit the musÃƒÂ©e du louvre where i was impressed  the same i can say about the fantastic  eiffel tower  both are the best to visit all over the word i visited  notre dame de paris palais garnier opera housesacrÃƒÂ©-cÃ…â€œur basilica and of course  the fantastic champs-ÃƒÂ©lysÃƒÂ©es in between others places  really have to go back  it was unforgettable days
5   1450177512
paris is my favourite city all around the world
68  1450177688
go to marais very nice area with lots of small shops and nice restaurants
36  1450179319
love romance croissant fromage women men animals trees architecture museums theatre music culture cafes a river citroen 2cv free elections paris has them all and more !  things to avoid: me since i like to travel without tourists around  if you need to read a review before deciding whether to go to paris you should probably not go
95  1450180024
champs elysee different squares ( saint lazare madeleine etc) lafayette stores fnac etc    i ate various plates &quot; du terroir&quot; of the french cuisine    taxis metro and train system are very well organized    hotel at such rate lik best western should provide at least a light breakfast
59  1450180239
paris is one of the greatest places to visitso much to do and see
66  1450180369
it was excellent overall
91  1450181866
hiire a bike  each 400 m a change point cycle along the river seine  visit place de monge just behind the panteon funny street with market
80  1450187318
the metro is a great way to explore paris a very good mode of transportation; one can never get lost taking the metro  but minus point here -- there are hardly any escalators or elevators in the metropeople with baby strollers and wheelchairs - got to find alternatives yeah locals are great friendly &amp; helpful eventhough english is not the main here but they are helpful enough to show the direction halal food are everywhere!! bakeries &amp; sandwiches should not be given a miss  one should try the hummer; a delicious toast sandwich filled with double beef patty top with sunny side up and salads the occasional rain (mid nov) didnt stop us from our eve walks along the republique sq awesome time getting snacks @ the monoplix (supermarket) love taking a stroll along  champs ellese with loads of souveniers should have stayed longer
1   1450187638
big city with relaxing atmosphere good food and great people
45  1450188498
in 5 words    beautiful dirty rude expensive smelly
46  1450189105
great exhibitions wonderful opera house ( i saw ballet this time) good restaurants but   you need to go to more expensive ones if you want really good food great shopping at fauchon but not for one on the budget it is expensive but the products are excellent  and delicious  apart from that just walking in beautiful parts of paris is very magical    i have visited many times and will be back soon !!!!!
99  1450189505
my favorite place is eiffel tower it&#39;s amazing!
30  1450189609
the louvre is a must see and the metro is a great way to get around
43  1450189871
the overwhelming amount of pasteries restaurants coffee houses markets bakaries  too good to be true! the french are in fact nice and helpful! paris is paris!  the prices are a bite high even on not touristic areas
8   1450190670
walking around the notre dame area before sunrise  we got some amazing photos and it was so quiet at that time of the morning  taking a stroll from st michel and ending up at the champs elysses and finding a wonderful christmas market  its a lot closer on foot than i thought
86  1450190928
les galeries lafayette for the buzz of the shopping  don&#39;t stay too long it can get stressful at christmas times  enjoy walking in paris and try something new musee de l&#39;art romantique go to a show have a croque monsieur in a typical parisian brasserie  linger in that brasserie take your time paris is also about living slowly
2   1450192094
everywhere you go there is another famous building or view eat in small traditional restaurants off the main streets for great food at good value make sure you buy a carnet of tickets for the metro (10 tickets for  Ã¢â€šÂ¬15 - each one is used for one journey but you can share them between your whole party
35  1450192222
two concerts with ben waters  visit to the picasso museum and walking around the city
74  1450192324
the whole experience was great lovely walks things to see and very nice people loved it the eiffel tower was a great experience although i would say we had got  all excited about the champagne bar at the top and went up for that but was very disappointed at the  little hole in the wall  out in the cold and plastic glasses  so we didn&#39;t bother  the moulin rouge was our highlight of the trip very much what i expected  very random think it could do with a little update but not to much didn&#39;t like the snakes but i don&#39;t like animals being used for entertainment and the drinks are well over priced but again i had researched this so wasn&#39;t surprised but it is a rip off people would buy more if it was a reasonable price 100 euro for one bottle of fizz 70 euro for wine pricey but please don&#39;t let this put you off it is still a great show and worth seeing
59  1450195399
paris has changed enormously during the last 50 years i have been visiting  in the 1970s and 1980s i found parisians had an attitude and not particularly friendly to visitors but on this trip i didn&#39;t find this to be the case parisians were very friendly and they spoke excellent english while my ability to speak french has declined through lack of use their ability to speak english has improved remarkably paris is known as a beautiful city and there is much to explore did a short walk in the 10th arrondissement and thought the small businesses delightful - small fruit juice bars coffee bars tea shops restaurants the neighbourhood was thriving and filled with small neighbourhood shops and restaurants would like to spend more time exploring the city on foot and by bike on a future visit at dusk on a blue sky winter&#39;s day went on the large ferris wheel at place de la concorde and views over paris and down champs-ÃƒÂ©lysÃƒÂ©es were spectacular  i have had poor meals in paris on previous visits but on this visit i ate very well; the food was very good
6   1450195658
the city is wonderful very easy to go around on the metro (27Ã¢â€šÂ¬ for a three day pass) food is incredible but beer is expensive (6Ã¢â€šÂ¬ a pint) best thing is to buy a bottle of wine (15Ã¢â€šÂ¬ lasts longer and is good quality) stay away from the people with a sponsor form for the disabled who ask &quot;are you english?&quot; its a con overall a fantastic place and also very walkable
83  1450195735
enjoyed everything accept accommodation
0   1450198020
i like chateau rouge cos of african food
93  1450202448
great location
19  1450204386
one of the great cities of europe paris has a very special aura and feel only there for 2 days so far far too short a stay metro is cheap fast and convenient  expensive to drink beer and wine which for perhaps the most famous wine country in  the world is bizarre   truly great steak restaurant in montparnasse region les grillades de buones aires argentinian  not french make the effort but don&#39;t eat beforehand  avoid the non licensed taxi drivers at the stations and airport unless you want to get rogered financially
63  1450204687
loved eating at cafe du 2 moulins and getting parisan style breakfast at local cafes in montmartre area really interesting museums allowing you to see amazing and famous artwork christmas lights and decorations around the city are beautiful at this time of year and we enjoyed lots of mulled wine!
33  1450205904
visiting paris just before christmas was exceptionally magical weather was also perfect  the christmas market all along both sides of champs elysees was beautiful and really gets you into a festive spirit  three days were enough for a short romantic stay  we did the big bus tour hop-on hop-off on the first day to give us an idea of the place and get us oriented  in the evening we walked from notre dame louvre magic eye in place de la concordechristmas market along champs elysees and saw the arc de triumphe  on the second day we visited the eiffel tower bateaux mouches sacre coeur and montmarte  on the last day we visited the galleries de la fayette saw the garnier opera house and the notre dame  in between visits we were always positively impressed with the rich french cuisine and tasted the croque monsieur croissants onion soup pate foix gras escargotall in all it is a highly recommended place for those who appreciate architecture style and gastronomy
50  1450206151
best locations transport system &amp; easy transection
73  1450215719
love paris everyone should go at least once in their lifetime it is very easy to get around and the metro is very good value only Ã¢â€šÂ¬7 for an all day ticket in zones 1 &amp; 2
13  1450216766
seeing notre dame and climbing the eiffel tower in clear december weather feeling safe with high security presence everywhere i went
41  1450252068
the architecture and the general feel of the city
27  1450252178
i visited notre dame eiffel tower n versatile palace no time to see anything else fairly easy to get around but not knowing language a hinderance
42  1450252728
magnificent city so interesting and easy to walk long boulevards for hours  attractions amazing and so many to see i was blown away by the cathedral architectureeven the less known ones they are always open and welcomingexuding history and great for photos shopped in supermarkets for amazingly cheap breadcheeseham and red wine gare de norde a great place to get around access beauvais airport by train Ã¢â€šÂ¬14 thereregular buses Ã¢â€šÂ¬2english language not a problem read hotel reviews carefully to prepare you
36  1450253943
nice city to visit with family
90  1450254451
perfect
92  1450254920
the people the buildings the history
85  1450256189
we did a guided walk which was an excellent introduction to the city
43  1450257313
well i know paris as i used to live here and shall be coming back for another visit in a few weeks! i recommend the bars around the rue de roquette area off bastille look for leche bar down a side street to the left for a big surprise! ;-) i also highly recommend the bars and restaurants along the canal st martin and the parc de la villette for a fabulous science museum a 360-degree cinema amazing vistas etc
28  1450262856
for a long weekend we took just 1-2 sights for a day and other times just relaxed enjoyed good food and wine excellent for a long weekend anytime of the year! dinner in eiffel tower is a nice experience
32  1450276406
this was my second trip to paris and i love it  this time we went for the christmas markets and shopping  the christmas market on champs-ÃƒÂ©lysÃƒÂ©es was great  we stayed in the latin quarter which is my favorite part of paris  i recommend you do some research and decide what area you would like the most  we chose the latin quarter because of the restaurants that were in close proximity    before coming download the &quot;paris by metro&quot; app for your phone  this app makes navigating the paris metro much easier i highly recommend it  if you are going to be out for the day and don&#39;t want to spend a fortune on lunch go to a boulangerie (bakery) and get a baguette with ham  it&#39;s the perfect lunch on the go  overall it is what you make of it  go with an open mind and be ready to try new things
88  1450283922
the hotel was in a perfect location not too busy but enough to solo walk late at night a few minutes walk to station and about 10 minute walk to the shops big shopping center nearby actual hotel was brilliant perfect for young couple view of the eiffel tower was amazing  staff very attentive breakfast in bed every morning was lovely best weekend i have had in a long time  just wasn&#39;t long enough but perfect for a quick getaway
27  1450299091
eifel tower
68  1450310414
it&#39;s hard to run out of things to do in paris as a tourist for art the museums are pretty unbeatable metro system is punctual and cheap within zone 1 so easy to get around the city is beautiful with nice architecture food is generally of good quality
66  1450312154
the eiffel tower mona lisa etc  traveling was easy  wouldn&#39;t avoid a thing
93  1450313609
notre damechamps elyseessacrÃƒÂ©-cÃ…â€œur basilicalouvre (musÃƒÂ©e du louvre)eiffel tower (day and night view)night cruise
24  1450334983
everything is beautiful here even the people are very kind and polite i love eiffel (as big and awesome as i expected before) louvre versailles and all the boutique stores i love every part of paris dont forget to try crepes waffle and churros
62  1450335748
it was easy to get around  the architecture and history is wonderful and the people were friendly and helpfulthe louvre was still shut but the musee d&#39;orsay was great we ate in several cafes and restaurants and the staff in all of them were great
60  1450339296
it is a great city with the historical buildings and monumnets good public transport  but u gotta be careful of the gypsy and pick pockets
4   1450339784
louvre was the best
4   1450349882
full of surprise everywhere
26  1450351831
eiffel tower and champs d elysseshalal lebanese food around parisvery convinient to move aroundwill come back again and people are friendly and helpful
64  1450353439
paris is and always will be the city of lights and love just love it when we visit
87  1450358060
just walk to the small street find your paris in paris dont wast time to see main attractions
34  1450361284
i think that its a romantic city and seeing the eiffel tower is a must     we had an amazing time
42  1450361338
road network and hospitality of people of paris
13  1450361437
the food ease of travel and the magnificent museums
56  1450361881
go to &#39;le pichet de paris&#39; off the champs-ÃƒÂ©lysÃƒÂ©es for dinner! expensive but well worth every cent! a lovely place for a couple - my girlfriend and i loved it!
38  1450364265
moulin rouge  eiffel tower restaurant 58  the louvre museum  notre dame  fouquets restaurant   champs elysees  metro is great clean and well lit  all paris street cafes are great for quick bites  shakespeare and company book shop
89  1450366657
paris has great public transportation and beautiful place to visit
46  1450369773
eiffel towers
86  1450370363
auch wenn man nur geschÃƒÂ¤ftlich 1-2 tage in paris verbringt hat man immer die qual der wahl was soll ich mir noch anschauen wo gehe ich essen und es findet sich immer etwas! dieses mal die ÃƒÂ¼berwÃƒÂ¤ltigende andy warhol ausstellung und ein familiÃƒÂ¤res abendessen im &quot;le train bleu&quot; freue mich schon auf das nÃƒÂ¤chste mal
61  1450371499
we fell in love with french people who were very helpful were trying to speak english in order to help us with the directions; there are so many nice places to visit!!we definitely recommend paris to visit !
62  1450373584
the best way to enjoy paris is to update your language skills to bonjour merci etc the basics will revolutionize your experience the french appreciate people giving it a go and have no time for people who expect english especially native english speakers get off the beaten track get a translating app and eat in some places with no english on the menu if it&#39;s filled at lunch&#47;dinner time with lots of beautiful people wearing (designer) black clothes it&#39;s probably amazing   give the plat du jour a go get to some real markets and test your new french out remember where you found your hidden jems (montmartre is my favorite place for this) for your next trip and get ready to fall in love with paris
72  1450374989
moulin rouge visit excellent and  from top of arch du triomphe magnificent view of paris
45  1450379814
great sightseeing and all round enjoyable experience!
77  1450381978
going to the top of the tower at night     indiana cafe was a real surprise and would recommend     yes we paid for 3 day travel card for around 25 euros each     the pick pockets lol
40  1450391589
i like the air of paris!
59  1450395212
actually i&#39;m still in paris hotel malar was my first leg of the trip i took a walk past the eifle tour my first day back in paris which was a lot of fun i almost hired one of the bicycle cabs to take me to palais de tokyo because i needed to get there for an experimental concert&#47;exhibition sunday night and i was running behind schedule     speaking of palais de tokyo that venue was exactly what the artist needed: it was large enough to hold the sizable audience yet intimate and approachable    to be continued since i&#39;m still in paris
47  1450397637
we loved the view from the top of the eiffel tower (worth pre booking a time slot!)   the fran prix supermarkets were a life saver in our self catered accommodation  the hop on hop off bus tours were a great way to travel about the city
46  1450418279
the transport system is fantastic the shopping malls are great and there are many tourist attractions
21  1450426826
fantastic sushi restaurant buffet at a fantastic price nearby with excellent fresh quality food
23  1450427587
very well developed infrastructure well connected with local transport however availability of local transport in early morning is a challenge and so is the understanding of english language in paris
22  1450433605
i took my girlfriend to paris for her 30th birthday so it had to be romantic paris  we fitted in to our short stay the eiffel tower notre dam a boat trip on the seine moulin rouge and sacrer coeur and ate out at several restaurants the few days we were in paris we loved it
45  1450434738
use the metro to get around cheap and frequent service hunt out the smaller cafes and restraurants just of the main streets and you get a real feel for the culture dining with the locals! plan to see the eiffel tower and arc de triumph at night all lit up and stunning! the louvre is a full day out with lots of walking!
89  1450450043
i&#39;m in love with paris! !!! it&#39;s perfect but s&#39;one took my phone so pay extra attention!!!!
5   1450452125
favourite places includes eiffel tower &amp; disneyland    public transport such as the metro &amp; rer is good but generally system is not very user-friendly with the less fortunate &amp; handicapped people can have more escalators for the convenience of users with luggages
65  1450454779
skating on level one of eiffel tower - great experience ! the markets at bastille some of the freshest produce - esp if you&#39;ve got cooking &#47; fridge facilities    get a carnet  - 14:10 euros for ten tickets for the metro      take a look at galleries lafayette for a christmas tree experience like no other !   and the chrissi markets that go from concorde to arc de triomphe !   fabulous - mainly cash only  esp for food !     the hop on : off bateau river bus makes a great alternative for half the price of a bus     and lastly - keep belongings covered at all times  otherwise enjoy !!!
4   1450463757
must try nutella crepes effiel tower at night and christmas market near the arch     metro and buses with 1&#47;3&#47;5 visitor day passes are easiest way to go around     most of the people are still unfriendly especially if you don&#39;t speak french but you can still have fun
99  1450464251
paris is really picturesque but it just too bad the city filled with litter
62  1450470160
buying an x-day public transport pass frm the ticket office at gare du nord would greatly facilitate travel     if you are also buying the paris pass (or something similar) make sure the public transport validity days don&#39;t overlap or you&#39;d have two passes for essentially the same purpose the pass was great for river cruise and museum entries though there were no large queues at many of the attractions this time round     paying for a full-day tour to paris disneyland (transport to and from) turned out to be a costly mistake a lot of the attractions were closed for upgrades we were done by about 2 pm but had to wait till 645 for the return leg of the coach pick-up it meant spending half a day just wandering about
0   1450471673
metro is easy and cheapest way to get around paris
96  1450473862
our trip wasn&#39;t long enough in 4 days we were walking to a local boulangerie for pastries and coffees shopping as locals and visiting sights as tourists perfect paris has everything you need!
87  1450477901
sheer beauty of this city wonderful galleries and exhibitions great architecture metro - quick and clean food delicious - esp baked goods: wonderful flaky buttery croissants and excellent coffee friendly atmospheric vibrant brasseries and cafes lots of japanese restaurants among other cuisines
98  1450487245
my high points were the enchanting christmas village the excellent food and of course the shopping
79  1450513563
we ate at the hotel restaurant  breakfast is nice !very clean !its easy to get around in paris the staffs in reception is very helpful when we asked where we can get tickets in metro train
20  1450514385
a jewel of architecture
51  1450519607
the centre of paris is very walkable - we were able to walk from our hotel near canal saint martin down to the seine (via place de la republique place de la bastille rue des rosiers and the pompidou centre) and then criss-crossing along the river to see notre dame the latin quarter the louvre the champs elysee &amp; arc de triomphe and the eiffel tower slightly exhausting but a great day out we ate at sur un arbre perche on a friend&#39;s recommendation and it was fab i recommend all of the above!
26  1450523557
such a beautiful city musÃƒÂ©e d&#39;orsay louvre and tuileries garden and the christmas markets from concorde up champs elysÃƒÂ©es avenue
94  1450528138
the metro was the fastest way to get around we queued for very little walked straight into the louvre the sacre coeur basilica was a must climb the dome when you are there for amazing views
60  1450528524
we were only there two and a half days but we managed to see a lot on the first day we visited the area of les halles (which is a bit of a building site) opera montmartre (where portrait artists followed you every where) place de la concorde champs elysees to see the lovely christmas markets trocadero and tour eiffel and walked from there to place saint michel we loved the quartier latin where we ate both nights on the second day we did the promenade plantee just off the bastille (which i fully reccomend to every one) place des vosges and le marais musee d&#39;orsay and we went to give our respect to the bataclan (which was very very emotional) overall a very pleasant stay with very strong emotions
57  1450530365
the french people were very friendly  it is very easy to get around either on the metro or bus service
34  1450530968
lots of great places to see in paris could visit 1 million times and still fond new things to see and do loads of great eateries and using the metro very easy to get around  very friendly people who were more than willing to help us find our way and make recommendations  we ate at a fantastic restaurant in the latin quarter with a secret menu can&#39;t wait to visit paris again!
22  1450531272
not safe city pick pocket widely happen especially at train station the station is dirty and smelling     city is not clean as 10-15 yrs ago i prefer switzerland although expensive but country is very clean people are more friendly compared people in paris
4   1450536389
we felt it important to show solidarity with the parisiens after the recent terrorist attacks&amp; this was appreciated  a &quot;highlight was lunch at deli-cieux printemps rooftop cafeteria with brilliant service &amp;  good terrace panoramic views  we visited notre dame  sacre couer  pantheon  la sainte chapelle musee d&#39;orsay st etienne du mont and the luxembourg gardens  travelling around was easy as bus routes are interlinked &amp; clearly numbered &amp; signed  preferable to metro as you get an overview of the sites we ate at several different reasonably priced bars &amp; restaurants  our favourites being la methode &amp; il gigola both in 5th arrondissement  we suggest buying bus tickets in advance at any tabac
20  1450541780
liked the louvre museum and notre dame cathÃƒÂ©drale mÃƒÂ©tro station link all thÃƒÂ© city u can move around easily   hated the smelly streats and its really crouded for once looking fir quite vacation
2   1450546512
eiffel tower notre dame we ate at a little french restaurant in st michel in one of the back streetsfound it very confusing on the tube to get around paris very unhelpful at train station french people are very disrespectful to people who can&#39;t speak french i was in tears trying to find out what train to catch to get to my motel no-one would help finally found a lovely lady who spoke english  and she made sure we got to the right platform
17  1450551120
the louvre and eiffel tower
36  1450551779
the musee d&#39;orsay is well worth a visit as are the louvre and the sacre coeur the metro makes everywhere within reaching distance
42  1450552299
parisians are not friendly even though i visited paris a few days after the recent tragic events and did not cancel like many other people did they were not too welcoming
49  1450593502
once you got yourself settled it was relatively easy to find your way around walking mostly
70  1450604250
louvre (musÃƒÂ©e du louvre) champs-ÃƒÂ©lysÃƒÂ©es eiffel tower musÃƒÂ©e d&#39;orsay
50  1450605816
fat tire tours excellent - eiffel tower summit trip plus cycle ride year round  superb guides   fabulous pastries   fabulous buildings
83  1450609294
the best places are off the beaten path away from touristic areas
87  1450611417
beau grenelle shopping center in easy reach on buses down the flat  parc a citroÃƒÂªn with the ballon de paris experience  cinema chaplin just round the corner !  and paris theaters not so far away!  (avoÃƒÂ¯d arriving at the week-end  it is a drawback to have to fetch the flat key at the hoetl next to gare de l&#39;est before seeing the flat and checking what&#39;s in and the state of it)
23  1450616039
museums
28  1450621201
i stayed around place vendome very charming place  try the restaurant at the park hyatt excellent food and the staff and great    on a good day go for a walk towards place de la concorde and from then towards pont alexandre iii a gorgeous bridge that features in almost all commercials shot in paris and continue towards les champs elysees    enjoy
4   1450624918
we did all the sites and walked&#47;got the metro around the french people were fantastic and very kind even at my poor attempt at the french language! we ate in many bistros and cafÃƒÂ©s and went to the christmas market at the bottom on the champes ÃƒÂ©lysÃƒÂ©es all were fantastic
73  1450625065
paris is a wonderful city  i always go there as i am doing a masters in law and at the same time i go for legal training courses  i speak fluent french which makes it somehow more interesting  the food and patisserie are wonderful in paris  life is great with nice museums history and theaters  there are plenty to see and the walks are fantastic  i like walking in the streets of paris which makes it a very interesting experience
71  1450625448
eiffel louvre sein river arc de triomp and taling the hop on hop off on foxity was worth it wish i had more time  and more money
22  1450627018
loved all the landmarks great transport system and good brasseries&#47;coffee shops
68  1450630012
paris is overrated and way too expensive to eat outside at restaurants!too much smoking everywhere - not so impressed with the city
25  1450631743
walking along the seine finding the statue of liberty! trip out to versailles many very reasonable cafes and restaurants to choose from tram and metro easy to use and also the rer line for visits further afield a visit to the philharmonie 2 formally the cite de la musique museum was excellent not to be missed
90  1450631997
loads of security in les grands magasinspolice everywheresuch a shame but welcome in circumstances  great food as everles gourmets de ternes magic of coursetry their now 2 london places n new find il corte hidden in st honorevery good  love citadium    low pointeverybody smokes outside buildings ruined outdoor cafe cultureone great coughathon!  awful
30  1450632033
was able to see the major attractions in the time available eiffel tower and sacre couer were excellent
27  1450632061
stayed from 14 to 18 dec everything was amazing suprisingly we had great weather for this time of year beautiful breathtaking architecture and just something from a storybook loved the christmas market in champs elysees metro a bit dirty but to be expected in such a big busy city felt very safe walking around in paris
74  1450633537
paris is excellent
82  1450636199
i  stayed  for my business  trip to paris and back to london eurostar service was pleasant and  comfortable  and  hotel was ideal and convenient   i  made all my planned business appointments and  managed to  do some  sightseeing
79  1450636790
moulin rouge=expensive but you must do it  wouldn&#39;t miss anything we done for the world
8   1450637331
strolling along the river
34  1450639521
so quick and easy to get around using metro
49  1450640524
great city
4   1450644349
i had a short notice trip to paris met an old friend hotel was great a 5 min walk from the metro station in a superb location in a cosmopolitan part of town  great restaurants &amp; bars nearby i visited the eiffel tower summit notre dame champs elysÃƒÂ©e christmas marketslocal restaurants sitting outsidei also saw a parade of around a thousand santa&#39;s on motorbikes collecting for orphaned children highly recommend a visit !!!
2   1450645387
eiffel tower is a great place to visit   local restaurants are great   i would recommend the tour bus to get around and see all the great sights!   avoid the bike taxis!
57  1450647566
tour effel loffer museum arc of victory river cruise seafood language not easy to communicate in english easy to go aroundwalking is enjoyable
19  1450649788
while staying at this second hotel i visited le 104 centre; this is an arts complex that includes a book store that was hosting a lecture&#47;book signing i attended with some local friends; that signing was originally scheduled for 11&#47;14&#47;15 but was postponed due to the november 13th attacks in paris there were no dangers the evening of 12&#47;15&#47;15 after the signing my friends and i ajourned to a bar&#47;restaurant called grand central in the same complex  unfortunately the kitchen was closed when i was there but the bar was open  i highly recommend a drink they offer called the ginger monkey    my stay in the hotel de la mare also saw my return to the chocolate story museum in paris on boulevard de bonne nouvelle; i love this place and their ever evolving exhibits the history and insights and of course the chocolate making demonstrations of course the boutique is not to be missed - not only do they have lots of chocolate they also have apparel and plush toys based on their star-shaped mascot and many other things and if you&#39;re feeling adventurous why not try some authentic aztec styled hot chocolate? be warned - it contains chili peppers so it&#39;s not for the faint of heart!    to be continued
95  1450650398
it was very safe at all times and security was top notch thank you    favourite place dinner on the eiffel tower(memorable)    we ate at our hotel local brasserie at the champs-elysees opera area and at the galleries    walking to landmarks&#47;galleries was very easy and metro was great!!    avoid nothing just go and enjoy!
30  1450653294
there are many great museums in paris i brought a paris museum pass(2 days&#47;4 days&#47;6 days) in airport this ticket helped me to visvit more than five museums i recommand this ticket to you because it will make your trip much easier you don&#39;t have to wait in line to buy a ticket and also the cost of this ticket is less than the sum of cost of the museum tickets buying separately
87  1450657024
hotel very close to eurostar and metro easy to get around quick next door
39  1450666395
the metro system is a fast efficient and cost effective way to visit paris not only the main attractions but also &quot;local&quot; areas such that you have a feel and taste of the residential  experience
14  1450672277
good food and so many places to go
89  1450685650
cool to have such good location to manage appointments in paris
78  1450696312
there is lot of do and see in paris no matter how long you stay in paris it&#39;s never enough to explore the whole city if you remove the traffic from the equation it&#39;s a perfect destination to spend your holidays    having said that the rail network is extensive and affordable
13  1450701508
breath taking at night! easy to find vegan and vegetarian cuisine i recommend hank vegan burger and brasserie lola the subway was very easy to figure out though the city is so beautiful you might as well just go everywhere by foot so that you see it all! not many english speakers so having a guide would have been a little nice i enjoyed the catacombs which we reserved online so we avoided having to stand in line i also enjoyed the little streets and ally ways close to the notre dame which were full of cozy little fondue shops the line for notre dame was insanely long so if you plan to visit inside make sure you have time!
47  1450709152
loved the restaurant we had dinner at but i can&#39;t remember it&#39;s name avoid the train stations with luggage and small kids we were pick pocketed when getting kids and luggage onto the metro
87  1450710350
eiffel tower and montmantre are the most recommended place to go  i feel like i&#39;m belong to paris  love the landmarks and foods  easy access to attractions
33  1450712799
awesome museums unique architecture but quite dirty lots of beggars and homeless too many cars in the center
19  1450713054
we liked everything in paris the first time we went( two years ago) so we had to go back     we liked the food the chaps-elysees musee du louvre eiffeltower and all the other     buildings which are too many to mention and of course the friendly people who helped us     and made an effort to speak english as we don&#39;t speak french     this time around my visit was mainly  to take photos at night of all the places mentioned     and more
93  1450713087
sightseeing shopping food
7   1450724661
visited louvre the centre pompidou as well as eiffel tower etc they were very much what i expected loved the metro very easy to get around the city went to disneyland was amazing!!!!!
66  1450729334
this is one of the great cities of the world a centre of culture haute cuisine fashion and fun it has features to delight everyone of any age or interest you will never tire of this place and will want to return again and again
25  1450730822
paris is incredible!!! nice people fantastic buildings romantic atmosphere and perfect wine and food!
74  1450733745
no matter how many times i go to paris i love exploring the city it&#39;s so charming
74  1450734663
only stayed because of going to concertslocation was very good didnt have eenough time to sightsee unfortunately
59  1450736051
we purchased a one day hop on hop off bus tour with l&#39;open tour which was great to easily get around paris and see all the main attractions i highly recommend it but suggest the 2 day pass as there is so much to see and 1 day just isn&#39;t enough greet people with a bright &quot;bonjour!&quot; and they are more likely to respond if you need english bag checks and security are standard at major attractions - it was reassuring not scary cafes and restaurants are plentiful and varied with menus on display so you can decide whether to go in lots of beggars on streets can be distressing
77  1450754467
the night bus tour of paris was amazing everyone was seated on the open top deck of a bus and taken on a 2hour trip through paris it was the week of christmas with the exceptional lights and the christmas markets to enhance the views
74  1450772044
food foeigrathe best place to get foeigra
74  1450772054
my favouite places in paris were easily the eiffel tower and arc de triomphe the views were amazing but almost the entire city is eye candy    i mostly ate breakfast at my hotel which although most travel guides tell you not to do was much much easier than scouring the centre of a capital city for a cheaper cafe for lunch and dinner there are amazing restaurants all around paris for reasonable prices don&#39;t be obsessed with finding strictly &quot;french&quot; restaurants we ate french food at a texas themed restaurant just find a place that you&#39;d want to eat at that has reasonable prices     the metro is easily the best way to get around paris it&#39;s dirt cheap and it&#39;s about 100 times better (cleaner and more spacious) than the tube in london and covers pretty much the entire city stopping at most landmarks it&#39;s hard to get lost they&#39;re maps everywhere
85  1450773113
easy to get around paris metro was fast and easy to use accomodation find one near the city its better and worth the money if u wish to go around the eveningarc de triump champe elysse was the place disney land and the outlet center near it too
59  1450775060
with the hundreds of cafes around paris they all have breakfast deals you can find some cheap breakfasts this way  if you&#39;re prepared to walk up the 669 stairs opt for the stairs when going up the eiffel tower as the lines usually go much much quicker  be careful around tourist areas as there are lots of beggars and scammers ignore them all and watch your belongings they&#39;re professionals at what they do
29  1450781079
shopping  and dining street cafÃƒÂ©s flutes of champagne great views hop on hop off bustour
2   1450781982
i loved everything !simply wouldnt change a thing
19  1450782498
paris is a eternal destination never too long
22  1450784493
the places we visited and liked most -   1 eiffel tower - (offcourse) at day and nightmost charming place  2 seine river cruise at night - must do  3 place du tetre - lots of artists doing live paintings on the street you can get your portrait done by an artist  4 disneyland - (offcourse)  5 louvre museum - (offcourse)    and lots of other places we missed and couldn&#39;t manage to visit  :(    we didn&#39;t like some of the metro&#47;subway stations feeling of insecurity was always there all major attractions and major stations are always crowded
2   1450784793
if you want to enjoy in culture-artmusictheatrefood and walkingparis is the best place
7   1450785513
paris is a charming city with so much history you don&#39;t even need to visit all the museums a simple walk on the river banks and old streets is good enough to make you feel the mood of paris  the cafes and restaurants are amazing the food is so delicious a croissant and coffee can be just as heavenly as a fine dine meal    contrary to what i have heard parisians can be really nice and friendly  i am definitely visiting again and this time i will have to make more time to shop!
52  1450786068
restaurant and wine
84  1450790100
we go to paris quite a lot but this was the best the high points of our trip were the fazil say piano recital and a ballet performance at the garnier opera
81  1450791495
paris is a beautiful city but the presence of many pickpockets and gypsies everywhere   is a very turning off point for tourism
16  1450793795
pastries wine cheese the architecture their culture just loved it all
4   1450794097
just an incredible city it has the enegry that i have only felt in new york before its the right mix between city history scenery food people just perfect  loved it all
30  1450794947
the metro is very cheap and you can travel all over paris - north south east and west quite quickly and safely tickets can be purchased by self-service machine at all stations with english translation so no problem there it&#39;s very easy to ask questions in english now as nearly everyone speaks english especially the young people there is plenty to do and a good selection of different restaurants for all pockets! i found french people very pleased that visitors continue to come and enjoy the beautiful buildings
12  1450795521
after countless stays in paris it never fails to surprise
58  1450798207
we arrived in paris although i have been several times before this was my partner&#39;s first time and she absolutely  loves it and can&#39;t wait to go again although just over a month ago the parisians were thrown into the worst nightmare you would never think it go on paris
20  1450798340
there are wonderful places and restaurants is very difficult to chosse one we liked specialy the museum at the fondation louis vitton
38  1450800751
we found the westminster hotel a great place to eat it&#39;s so polite and welcoming while being a very luxurious otherwise there was always a boulangerie round every corner which was fabulous and good for vegetarian options we used the metro which was cheap and efficient but definitely grotty and smelly at best the metro is a confusing conundrum plan your trip in advance!! i would avoid the steps to the sacrÃƒÂ© cÃ…â€œur at all costs they are a challenge to say the least and be prepared to fight off the street sellers paris is truly beautiful at christmas and is naturally very busy! being high security at the moment be prepared to wait in line for the attractions like the louvre unless you book a few days in advance online but it&#39;s well worth the wait- even if we weren&#39;t very good at ice skating at the top of the eifel tower!
29  1450800886
paris felt very safe &amp; secure we did not mind the constant checking of bags etc  the high points were: the eiffell tower moulin rouge monte martre &amp; river dinner cruise on the seine
65  1450802272
paris is wonderful it is absolutely worth visiting i recommend going on the big bus tours&#47;city sight seeing two or three days is enough to visit paris and to do everything be aware of people asking for money under and around the eiffel tour they first start complimenting you and if you don&#39;t give money (or enough) they get really rude! also beware of the people selling souveniers directly under the eiffel they are also very rude sometimes rather buy a 100m or so on
19  1450804014
saturday i had dinner at le bistro marbeuf very good! i went back on sunday for lunch!
1   1450804826
very easy to get around via metro easy access from airport to city centre fantastic christmas markets and shopping! downsides- the food wasn&#39;t great most pastas were cold and tricky to send back
44  1459420465
restaurants &quot;polidor&quot; must be near boulevard stmichel and le chartier near grand boulevard and  boulevard haussmann
40  1459421682
very expensive  85euros for a taxi from the train station to our hotel which was about a 5-10 minute ride we got the train on the way back which was much much cheaper and just as easy  96euros for two double liquors in the pullman hotel - nearly fell off my chair! miserable staff who wouldn&#39;t even crack a smile and very rude i would avoid this hotel!!    price aside - paris is such a beautiful city with friendly people i could walk around for days without getting bored    the weather was gorgeous when we went so we bar hopped and sat outside - there are so many lovely little bars to do this in i would definitely recommend and definitely return (need to save for a bit first haha)
77  1459422970
well the perfect dinner we had it was at pizza hut close to pont neuf might seem crazy  but it&#39;s trueit was the best pizza i ever had   but in the french cuisine restaurants the desserts never disappointed   a patisserie that we loved was la parisienne they have about 4 locations   when you plan a trip to paris make sure you plan everything by your taste not what the internet suggest i mean if you are interested in art a lot or fun or social  what i really hated is that they are still so behind  we bought tickets online and printed them but in most of the places we had to wait in the same line like others- like if you need to buy tickets
96  1459425956
paris is fantastic! the beautiful flower markets near to the notre dam the magnificent architecture wonderful museums the children especially enjoyed the eiffel tower in the evening and leisurely strolls along the river seine  the parks are fantastic and especially beautiful coming into spring there are play areas for the children too  the shopping is second to none the gorgeous department stores; luxury brands french perfumes lovely homeware and toys! endless boutiques and high street stores and of course the patisseries  the only negative thing was that on arriving at gare du nord we were ripped off by a taxi driver so watch out!! ask how much first and make sure there is a meter in the taxi stay in the taxi line only get in a taxi that is in the queue is my advice metro is good once you suss it out good for day trips out walking is the best way to enjoy paris
45  1459428764
very easy to walk around the city  i was told paris was very expensive but we managed to find places where the local goes and been able to spend less than Ã¢â€šÂ¬45 for a three course delicious lunch with champagne for two people or tapas for three with three bottles of wine for only Ã¢â€šÂ¬70 that was a good added bonus!  parisians can be rude especially the one not working in the tourism business but hey ho that&#39;s in their dna  other than that it&#39;t a great place to have a little city break with friends didn&#39;t see all that romance around to be honest so i&#39;m glad i wasn&#39;t there with hubby
86  1459429213
all was fine
76  1459429302
the city of paris is very beautiful but i believe that it is not so clean comparing to other european cities like london or zurich the monuments and architecture of buildings in paris is much better as compared to any other city government should do a little more to make it cleaner there are so many people selling souvenirs by force which was annoying and should be stopped    the view of eiffel tower was breath taking its really a pride for the french people and they should do more to make it even more attractive     i kindly request the french government to consider providing &quot;halal food&quot; option to the muslim tourists visiting your country it will certainly increase the influx of more tourists from the muslim countries    visit to the champs ulysses street was unforgettable experience
41  1459429911
go to poulette for a well priced ( inexpensive for paris) great steak frites go early to angelina the afternoon queue is slow and very long try and avoid going when ground staff at the airport strike getting through border control was a nightmare
9   1459430039
disneyland good to view sights from the boat on the seine
71  1459430051
loads to do shame about the increase in beggars since last visit a few years ago
19  1459431211
montparnasse tower for a great view of the city including the eiffel tower get a two visit ticket so you can see the view in the day and night time     a night time river boat tour with dinner a great way to see the landmarks and delicious food!
14  1459431512
a bit dirty but beautiful buildings might be better without parisians service (in general) must not be a french word i am sure that with  time parisians will learn how to smile and be helpful rather than to make a face or strike by the way i also love the fact that by law service is always included you never feel guilty nor (this is the downside) the waiters feel obliged to to be good or just friendly !
60  1459431588
Ãâ‚¬ÃŽÂ¿ÃŽÂ»ÃŽÂ· Ãâ€žÃŽÂ¿Ãâ€¦ Ãâ€ Ãâ€°Ãâ€žÃŽÂ¿Ãâ€š
41  1459431640
nice city with lots of history architectural buildings lots to do and see!the down side is everywhere is crowded was spent more time to queue than actual site seeing
41  1459431906
the city was easy to get about whether hop on&#47;hop off bus metro or like us walking there was a vast range of cafes&#47;restaurants to cater for all tastes we had a dinner river cruise on the seine which was very worthwhile downsides paris very expensive and thought there was a general lack of free toilet facilities within the central areas
71  1459431945
disney land and eiffel tower while city is just amazing
61  1459432259
visited notre dame for first time fantastic experience  also enjoyed just walking around the streets of st germain and the latin quarter
41  1459432820
paris is an amazing city so much to do and full of character
14  1459433235
touristic bus is the best tool to visit best places in paris especially eiffel tower
70  1459433310
the batoboat hop-on hop-off ticket and the metro 5 day rover ticket were excellent value and made getting around really easy all major attractions were easily accessed by public transport  montemartre is well worth a visit but climb the hill for the best food and views
50  1459434678
eiffel tour  river seine  le louvre  disneyland paris  notre dame
24  1459435512
loved mon martre  excellent restaurant called restaurant victoire  great hotel!garden elyse
93  1459435661
booked a great tour (louvre eiffel and notre dame) through viator which was great!
28  1459436824
the museums were great  i bought a museum pass but i couldn&#39;t get much use of it do avoid mondays and tuesdays if you want to go to the museums
12  1459438215
beautiful city love the food love the lifestyle!
79  1459438275
even more beautiful than when last visited
37  1459438410
we have been to paris many times our family live there so we went for easter and a 3rd birthday
87  1459439242
loads of places to eat and things to see i love paris
86  1459440030
we were in paris for 3 days and as hobby photographers it was perfect    if you are travelling buy eurostar make use of the 2 for 1 tickets  musee d&#39;orsay  (2 for 1)  is a must you can buy the metro cards in london in eurostar lounge before you board     download the visit paris by metro app on your phone and it give you free metro maps journey planners and places where you can get entry with a metro discount  tour montparnasee (metro card discount) at night is great but you will pay 350 euros for a coke!     loved sacre coeur  ~ you can go up the funicular with your metro card or risk the very steep flight worth paying the 6 euros to go up the dome if you can climb 150 steps up and 150 steps down     btw the paris metro is a godsend and we used it constantly fast efficient and near everything look out for the helpful maps of local tourist attractions near the metro station     everyone was really helpful and friendly ~ very tourist friendly and most spoke english and tolerated my attempts to speak french
33  1459441768
an elegantfascinating city    don&#39;t be afraid to get off the beaten track and explorethe side streets hold some gems of restaurants and shops
59  1459441835
famous sights exceeds expectations really spectacular!
18  1459441933
paris is a lovely place full of nice architecture and lovely squares but parisians could be nicer to tourists they never seem ready to help i&#39;d go back to visit if i had another chance
1   1459441987
very clean very friendly but expensive too expected though for a city!
69  1459444294
use etickets for louvre less queuing
73  1459445080
the train from the airport can get a bit shady at times i will take a taxi next time
99  1459446802
it is not for everyone--try and be flexible  it can be quite depressing enduring the lines everything in repair strikes and aggressive behavior  i find the rest of france more interesting and friendly  paris is the land of rules and legalism some unhappy people always trying new ideas so do not expect things you know to remain the same  paris is very multi-cultural which is refreshing  it is a well worn city
67  1459448239
it was good to use hop on hop off bus to see the sights of paris lots to see and do with plenty of walking
66  1459450270
i loved almost everything only slight disappointment was the moulin rouge too modernised not traditional enough too international not parisian enough can-can very poor!
55  1459450856
i saw all the usual tourist places and climbed the 212 steps up to the montmartre church great view from up thereof paris the memorial laid around the place de republic for the victims of the french bombing was poignant but reassuring in so many cultures laying remarks of respect the cafes were lovely the cafe gourmand (coffee and pastry) was way bigger than i ever expected but demolished all the same! i found the people friendly and most spoke english which put my french to shame it&#39;s just a shame the city has so much graffiti and that it rained constantly  but hey i&#39;m used to that in the uk!
29  1459451711
beautiful city  one could stay for years and not run out of things to see and do  highly recommended  visit this remarkable city it is worth it in every way
15  1459453477
highlight of our trip was our first visit to the moulin rouge after a number of trips to paris i thought the admission was expensive but having seen the show it was worth it!we stayed in montmartre again having fallen in love with the area during our previous visits
60  1459454461
the art galleries were brilliant
98  1459457039
eiffel tower and eurodisney
83  1459474462
incredible city so much to do and not enough time explore marais montmartre louvre seine eiffel tower notre dame champs elysees and so much more
45  1459480201
good for sightseeing not there long enough to soak up the parisian culture and history found most locals rude and unhelpful was very uninspiring for me hop on hop off bus is brilliant!
86  1459485375
everything was great apart from the weather it was march a little cold and lots of rain     in terms of attraction great climbed eiffel tower visited notre dame louvre and disney     there was one cafe indiana near republique tram station and the food was awful 50 euros for 2 adults and 1 child and one main meal was cold rotting salmon and a potato still wrapped up in tin foil as it had come from microwave dont go there total rip off
52  1459499379
architecture and ambiance were great it was too crowded but it was spring break i would return at another time but not tourist season
12  1459500060
paris is a beautiful city i&#39;ve been three times now and still feel like i&#39;ve only scratched the surface of what it has to offer get an unlimited metro ticket for seven euros a day as the best way to get around the food and drink can be expensive so bear this in mind and parisians can be well less than effusive put it that way! but don&#39;t take it personally
7   1459500162
a city with so many things to do from parks museums shows or just to relax and have a nice walk or drink a cup of coffe a glass of wine in the typical french bars
48  1459504866
i am a bit biased towards paris given that it&#39;s the city where i lived as a child i have great memories there i would recommend all landmarks i had a meal chez leon on the champs elysees they have the best mussels in town and their desserts are delicious + the bill for two was actually quite reasonable had another meal at pizza vesuvio: another great place to eat
87  1459506236
we travelled by eurostar from london as two families with seven children between us ranging from 24(!) to 4 &amp; most ages in between! climbing the eiffel tower was incredible - my 4 year old managed it; hopping on &amp; off the batobus &amp; seeing the famous landmarks on the seine was wonderful; seeing notre dame was awe inspiring; visiting eurodisney was great fun - easy to get to on rer a double decker train; musee d&#39;orsay for impressionists was breathtaking oh &amp; this was all in three days including travel on what was possibly the soggiest ever week in spring!! it poured with the occasional outbreak of drizzle but with one day where there was a bit of cloudy sun!
83  1459506302
the trip on a hop up hop off bus
99  1459514059
there is an immense amount to do in paris tips:    1 try to speak frencheven a small amount - it brings out the best in parisiens    2 allocate plenty of time if you want to do the louvre properly- half a day wont cut it a full day at speed longer if you really want to soak it up    3 a lot is made of the pickpockets in tourist areas for the most part their ploys are obvious keep your money out of reach and never let yourself be surrounded by groups of strangers     regards    clayton (australia)
70  1459514146
paris is a city of contrasts beautiful at the centre and becoming more and more horrible towards the outer rim
21  1459515493
places not to miss versailles louvre tour eiffel notre dame sacret coeurthe restaurant bars markets museums
96  1459515578
lots great places to see
69  1459517075
a lively city
34  1459519571
metro efficient and reasonable and metro staff great customer service  a bit too much security screening at shops and places of interest  shocked at the amount of homeless people  a bit expensive but to be expected  very interesting city for anybody interested in history and architecture  positive experience all round
27  1459524759
there are two things one is paris in itself an the other is the idea of paris     the idea of paris being a romantic getaway that would somehow take you on a trance ride with your beloved has been sold by movies since times immemorial     add to that the charm of its bistros cafes fashion scene people watching and you think you have a deal     however and extremely sadly that is so not the case let me peel paris off one layer at a time for you     1) the fashion and people watching scene      i was in wien and the german part of switzerland for 12 days before i came to paris i was seriously impressed by the german fashion scene and in particular found wien to be extremely gracefully dressed paris fashion even in the high streets of champs - elysees and galaries laffayate was one wanna be two cheap and the french seem to be dressed in second hand clothes i tried to resist forming this opinion for good one and a half days but at the end french dressing sense and paris seemed to go hand in hand dated shabby uncared for and unkempt     paris and its people seemed as if they have stopped caring of their continuous downfall since at least 1960 and it seemed a society that has accepted a grace erosion as a reasonable trade off for their laziness     i could almost smell french values in contrast to the confident and preserved wien and for what it was i started pitying french since day two     2) paris the city    for all the musical romance casablanca has sold us lets get this straight paris streets are full of dog poop beggers litter smelly people and pillars you would pull your hands off if you thought off leaning so are the metro stations they give you a dated feel once you enter which soon converts into outright filth with coaches smelling of sweat paranoia of pick pockets not to mention people jumping the gates and travelling with out tickets it is so third world and all the paris dreams you paid for are damned     3) attractions eiffel louvre etc    eiffel stands out and you feel pity for her that it rests in a french place it is pretty amazing seen from the trocadero square however yet again once you walk down from trocadero towards champ de mars you see all the shell game cons countless hungry people selling cheap souvenirs crepe joints an over hyped siene and as soon as you walk onto champ de mars you see how all the gardens have been uncared and unlooked after tuk tuks trying to ferry you walk like pests till right below eiffel     when would the french realize a basic sense of preserving a city so beautiful of having seen the german part you clearly perceive the non application of mind into entire city planning preservation of museums and let enhancing be preserving the beauty a place has to offer     the only reason i feel eiffel is so pretty at night is because you see less of how much the french have cared less for it    the louvre is great it was one place i found was better manage than all other things     sacre couer with the con street in front of it saw swindlers freely playing shell games and three card monte then a man comes over and tries to sell me an stolen iphone     the germans had my brains and i thought the french would have my heart but alas the french only have my disgust     i can go on and on but truth be told paris simply sucks it is best for a long lay over and a visit to the eiffel nothing seriously else it sucks
99  1459540388
enjoyed it very much  but there could be tourist stewards like they have in endinburgh to guide tourists  you come off metro sometimes and don&#39;t know what street to turn too
34  1459540426
what can one say?
41  1459541496
great food friendly and warm people rich history lots to see and experience reserch prior to your trip to maximize your time and not miss out queues can be especially long for popular sites- louvre versailles notre dame etc  get your bearings early and learn the bus and metro system consider bus and train passes as well as museum passes- include value and convenience into your decision making as usage rates will definitely be key ( how often will you use the train or how many museums donyou care to visit? )
50  1459545046
opera house  seine cruise  louvre  nottre dame
88  1459546379
paris is not same as i thought city is dirty with thugs and pick pockers !unsafe for family need extra caution    have said that boat trip around effiel tower was the trip highlight never to miss this in your trip
61  1459551687
i recommend to eat delicious food in montmartre
92  1459554003
the architecture is lovely we really enjoyed the sight seeing and the night life
10  1459569488
most beautiful city in the world
13  1459580295
the bus trips around the city are great and worthwhile even when it is cold    versailles was disappointing - the queues were very long and the speed at which the tickets were sold at the palace was very slow  the palace is closed on \monday and on tuesday you only get music and no fountains and now they charge for the gardens on tuesdays and weekends - an extra euro 8 but they don&#39;t tell you that when selling the tickets  granted it was winter but to charge for the garden and have no fountains going is not value for money    generally i found the shops too hot for comfortable shopping and in many cases i just left and went outside for fresh air    the view from the top of the tour montparnasse was great
2   1459583086
my favorite places are eiffel and sacre cour  and its very easy to get around paris by metro and just by a mobile with gps map
27  1459585231
beautiful palace and scenary    the metro is not so clean though disneyland really is for kids
27  1459589734
ate v good meal at la gauloise opposite hotel 35 Ã¢â€šÂ¬ good value
78  1459590927
eiffel tower notredame fabourg st honore galleries lafayette culiner at le procope at st germaine les deux magot at st germaine
80  1459592655
a wonderful city lives up to all of its hype we stayed in the opera quarter right in the centre of paris we rarely used the underground or buses as almost everything was easily within walking distance from opera there is an amazing sight in almost any direction the city streets are very easy to navigate and to get used we mainly focused on the historical aspects of the town (both of us were historians) but there was plenty of modern stuff available as well     my only issue was that we couldn&#39;t find many genuine parisian&#47;french restaurants in the area but i think this was only because of where we stayed and because it was my first visit so i didn&#39;t know where the less touristy areas were hidden
91  1459599019
hotel bauchmont very nice dinner   hotel costes very nice lunch (celebrity spotting at fashion week)   cafe flour for a nice parisian cafe
38  1459600952
beautiful city everyone should visit !
63  1459602304
paris center
79  1459602666
as far as restaurants go you have to check out derriÃƒÂ¨re apartment de cuisine! great concept place and lovely food!
17  1459602674
taxis are easier to use and better value for a family of four
31  1459602713
paris is a lovely place  the nitre damne church is amazingall the tourist spot are really great
48  1459602813
site seeing   good quality restaurants
47  1459602995
if you really want to see paris fully - walk everywhere!
57  1459603885
amazing city difficult to go with a stroller
90  1459605682
i just fell in love with paris so much to see and do  such a romantic city most of the locals were friendly the hop on hop off bus is a great way to get around and see the sights  we did an evening cruise which was great simple a breath taking city
9   1459606263
if you ask me paris is the most beautiful city of europe there is no way to be bored in paris architecture culture shopping good food it has everything
31  1459608129
enjoyed our random croissant breakfasts  magnificent sole meuniere at the gare du nord restaurant opposite the station  and the best omelette ever in montmartre    avoiding the queues at notre dame and instead circumnavigating and so losing the crowds memorable    exciting and skilful taxi driving with sardonic world weary drivers brilliant
8   1459610397
just be ready to explore &amp; discover you&#39;ll never know what surprises you&#39;re gonna get Ã°Å¸â€˜ÂÃ°Å¸â€˜ÂÃ°Å¸â€˜Â
81  1459613525
so much to do you need three weeks to do everything
54  1459616024
great vibe in paris excellent shopping and restaurants cafes and markets    cultural art and theatre   you can&#39;t beat paris at any time of year
38  1459617595
eat at hippotomus for fantastic musssels
74  1459619181
very picturesque city with the views largely unspoilt by concrete and glass of modern day loads to visit and easy to travel around the &quot;hop on - hop off&quot; buses as in any large city are a useful way to explore and get one&#39;s bearings before narrowing one&#39;s stay to specific areas
98  1459622339
paris is great has everything you could ask for except the food is expensive and the wine glasses are too small!
41  1459623728
we loved the paris semi marathon bercy is very good with a reasonable choice of restaurants the sewer museum was a bit smelly but surprisingly interesting angelina on the tuilleries has hot chocolate to die for!
64  1459626929
public transport - underground is fast efficient very convenient  the architecture in paris is just a legend  during rainy season the city is less attractive
45  1459628758
visited all the main attractions used the metro to travel around very easy ate in french bistros and cafes excellent service
25  1459632050
eiffel tower
35  1459633099
lots of good sights fab restaurants&#47;cafes it&#39;s a compact city easy to walk around to see everything feels safe  st germain area is a good base  the marais is charming  galleries laffayette is a stunning department store with fabulous views from the roof terrace
68  1459634662
its paris
59  1459636207
easy city to walk around to get to majority of tourist locations food was everywhere and good architecture
54  1459640575
it&#39;s a beautiful city the history arts and the scenery are all very good
20  1459642115
it was easy to get around paris the only hurdle when we stayed was it was raining so we don&#39;t get to enjoy the view eiffel tower and seine river cruise is a must visit the pont de neuf statue and take a picture with the lovelocks around the steel fencei didn&#39;t get the chance to see moulin rouge since my companion didn&#39;t want to see the show i would really love to come back in paris to explore the place more i loved the japanese restaurant along pigalle it was tasty and has value for money you can have everything you need along boulevard clichy and along gold hotel
50  1459645185
the louvre was a must see  but get tickets beforehand to avoid the lineup pay attention to signs or you will get lost
9   1459647541
loads of places to visit
81  1459658907
easy to spend 4 or 5 hours at the eiffel tower take a couple of beers some wine and cheese and get there early watch in awe this incedible structure while the sun goes down watch in amazement when it lights up at dark and then be totally delighted when the lights twinkle a real highlight!
57  1459661281
i love paris the weather was wet wet wet even with the weather though paris is great the metro makes travel very easy although there are lots of stairs so be prepared for lots of heavy work if you have luggage overall quite a wonderland whatever your interest
37  1459663108
champs elysÃƒÂ©es  -restaurant leon - amazing muscles !!
56  1459664042
next to being the city of love &amp; light paris is also the city of walk my advice: if you want to see the beauty of this city: walk walk walk most attraction are close to each other right at the middle of the city &amp; if you walk longer than you expected no worries the metro network is just perfect and well spread to take you back
41  1459667478
amazing place to visit or spend the holiday season
58  1459669678
i liked the atmosphere of paris in le marais neighbourhood near the picasso museum i had a nice beaujolais red wine in a cafe called le wood
46  1459670008
amazing and romantic
18  1459670704
i know paris very well and love itjust wish people were more polite the uber taxis were marvellous very reasonable so many lovely places hard to choose from
60  1459671103
paris is beautiful but the parisians are not so friendly and helpful that is what i hate the most about paris
17  1459673102
paris has always been a magical city but recent tragic events seem to have brought its citizens together in a celebration of all that their city has to offer
69  1459673987
the metro was quite daunting with very poor directions and very unfriendly staff and one did not feel safe on the trains
29  1459674595
ok
56  1459675207
so many sights to see you have to plan out your day to enjoy them all carefully cab drivers were very helpful and cabs were not too expensive  beggars are everywhere and can be aggressive they will follow you and yell insults even   they will bother you at outside cafes our day was filled with them bothering us at every resturant and site except inside the louve  be careful of the pick pockets they are everywaye as well
70  1459676328
versailles bike tour and paris bike tour with fat tyre bikes was awesome going to a local market biting fresh bread and cheese nothing better!!! catacombes is also an experience i will never forget!!
75  1459678411
a crowded beautiful city :)
20  1459679568
great food friendly people world famous and iconic landmarks museums and general things to do
18  1459679799
a great place to explore
40  1459681073
the marais is a marvellous part of paris lots of galleries shops nice corners to sit and enjoy watching people we enjoyed an interesting diner-spectacle in cabaret equinox listening to young performers our favorite view is from the top of the institut du monde arabe we recommend a visit to the impressive new cathedral in creteil easy to reach by metro
21  1459681250
places with halal signs
71  1459681270
le grand colbert restaurant in the 2nd arrondisment is a must!
71  1459684310
the building is very beautiful  the paintings are very inspiring the weather is also very pleasant not too cold but rains a lot and people are also very nice very friendly
70  1459684892
excellent location plenty of grocery shops bakeries restaurantsall in very short distance almost on the door step safe neighborhood walking distance to louvre pompidou operaand if you like the walk notre dame very comfy bed new kitchen toilette fridgewhole apartment newly refurbished clean and quiet not a best view but who needs a view in paris when one should be outside and stroll around day and night :))  excellent wi-fi we asked for late check outno problems at all we just had to let know staff 24 hrs before the check out close proximity (max 500m) to subway les halles(one of major subway hubs) for families which plan going to disneyland best and easiest way to take subwaystraight line with subway a4 from les halles to disney would definitely go back again!
25  1459686504
the toures   the walking  the food  the site seeing
7   1459687356
underground travel is very busy during working hours eiffel tower is a must but gets busy so visit early
31  1459688711
hotel was great central location good shopping and sight seeing moulin rouge was fantastic
54  1459690897
loved walking through the streets - great food and really accommodating for the kids! loved the tower!!!
29  1459690922
eiffel tower and moulin rouge are a must do when in paris
32  1459693376
had a fantastic meal at la jacobian
46  1459694696
didnt stay long enough  ive been there before so i didnt need to hit any of the major tourist places  lots to do very walkable city when used in conjunction with the metro  people friendly and accomodating even when our french was poor  market at st ouen was fantastic found some great bakeries and a surprise of a middle eastern joint that was frightfully good  buying tickets at metros very confusing to anglos though i was by far not the only one looking really confused about what the hell to do or how to buy tickets
75  1459694764
good restaurants
80  1459695455
came to see several art exhibitions
90  1459695688
the sights are great and the bars and restaurants are cool and hotel paris france is very central
1   1459696334
paris so beautiful and fantastic in all weather easy to walk around with lots to do not always with small children tho
40  1459697489
notre dame sacre coeur saint chapelle the louvre catacombs pantheon eiffel tower luxembourg gardens eat in the indiana cafe crepere
25  1459698088
my wallet was stolen on this trip so can&#39;t be a good experience
47  1459701999
driving around paris was a disaster we were welcomed by romanian gypsies who were aggresively asking for money and attempting to break our side car&#39;s mirror be careful as well because they will try to steal your belongings and money even you are inside the siteseeing tour bus
2   1459702098
whenever we go to paris we enjoy every single minute because there is so much to see and do its charm is so attracting and no terrorists and evil people can stop us from visiting it   we tried all kinds of food including french turkish and chinese and liked them all  we were most pleased with rer and metro because they helped us get where we wanted without wasting time (and money)  in the evenings before going to bed we discussed the plan for the next day and had a plan a and plan b (depending on the weather) but we managed to see 4 museums versailles and all the popular sights unfortunately there was no time to see the chocolate museum and monet l&#39;enfants exhibition but there must be other opportunities to continue
48  1459702378
amazing paris a lot of nice and kind people! at least once in life everyone should visit paris!!! please be aware on our way back to uk refugees or bad refugees blocked the road it was impossible to get through without having the police there to clear the road keep calm in your cars locked and avoid eye contact
6   1459702451
paris is a very beautiful city with lovely renaissance architecture both inside and outside the buildings great to try my gcse french from over 20 years ago and even though i was a little rusty i managed to find my way round the metro system and back again !!!  people are friendly and help you with your french  the eiffel tower was breathtaking and so was the arc de triomphe and the louvre with the pyramid  would definitely go back
87  1459703519
a beautiful historic city though sadly there&#39;s not much to do when it rains except museums three days would be plenty to cover the best paris has to offer plus an extra day for something outside the city like disneyland or versailles palace     the heightened security around the main attractions is a bit of a pain though it&#39;s reassuring at least! we&#39;d recommend the hop-on-hop-off big bus tour (on a dry day) as it gives a great overview of the city&#39;s highlights in less than 3 hours though if you have the time and stamina to cover the same ground by foot it&#39;d be just as comprehensive and a lot cheaper    as for breakfast you&#39;d be much better off getting baguettes&#47; croissants fresh from a bakery rather than overpaying for a poorer version of the same thing in a hotel for lunch&#47;dinner the mid-range restaurants generally offer decent food for good value if energy levels drop between meals there are endless cafes in practically every street where you can rest your feet and soak up the atmosphere until you&#39;re ready to take off again!
5   1459704151
paris is a great city for sightseeing the transportation is convenient to people who is new here and you can find information everywhere to ask for something
69  1459704757
latin area is central excellent for tube travel
53  1459705869
i had already sojurned in paris some years ago and i found it as fascinating as in the past walking through the long avenues visiting the museums and moving up and down with the metro allows to fully exploit the time  i have eaten in french restaurants as well in italian ones and was always satisfied  as i told moving through paris is very simple and i suggest to use the sightseeing buses hopping on and off in any place and  then restarting
20  1459706526
love the place  =)
1   1459706570
take a decent brolly with you
0   1459706867
go out and about as much as you can its a beautiful city with so much to do
32  1459707207
mugging dirty and miserable city
34  1459709204
i love that city its architecture its museum its restaurants
69  1459710226
thoroughly enjoyed all sites visited  long queues at eiffel and louvre  entrance prices good value  remember id for minors to prove age if under 18&#47;25    recommend boat trips on the seine from pont neuf  only problem which couldn&#39;t be helped was the continuous rain
86  1459712490
like the welcoming of people specially when one makes an effort to say some words in french they go out of the way to help you    the security all over paris after the terrorist attack in brussels is excellent i hope it continues    i saw a couple  sending a 10-13 year old to beg outside blanche metro ar 12 midnight how could social services allow this to happen? talking to the mayor of montmatre  he said &quot;that&#39;s democracy&quot; i think he was being a bit sarcastic sorry but that&#39;s like going backwards the laws of the country are there to protect the vulnerable in any society     i loved the food
42  1459714816
eiffel tower is beautiful but many pickpockets in the metro and some around the tower
84  1459716913
although our trip was well planned many of the visits such as a boat trip on the siene had to be cancelled due to the wet weather queuing for 90 minutes to go up the eiffel tower was disappointing we had to make a change travelling back to cdg airport due air strikes with ground staff be prepared for queuing and strikes on a positive note you will be amazed at the many beautiful buildings and artefacts
0   1459721219
i loved everything  i hated that many many places and streets were in repairs places in disneyland also
16  1459721323
paris is very beautiful   the food is the best in the world   people are very friendly  the architecture is incredible
17  1459724105
all the major sights are walking distance of each other - sacre coeur-louvre-etc easy transport system download city mapper for ease people were pleasant make sure you go to the top of the sights the queues are long but definitely worth it don&#39;t get caught up at the first souvenir stand you see look around you will find bargains same for selfie sticks lol they sell them for so cheap    breakfast in america is amazing near the cathedral notre dame amazing service also    just walking around paris with your gps is lovely really enjoyed doing that and feeling like a local i&#39;m sure you&#39;ll love it if ever in doubt just grab and uber -- its definitely worth it
23  1459730483
other than the dirty rotten lowlife who stole my wallet paris was wonderful  ladies beware there are a lot pickpockets out there           the eiffel tower 10:00 pm light show is spectacular and the elevator ride to the top is enthralling  the museums and churches are spectacular as always  take a ride on a seine boat tour  i was a little let down by the antique markets as their prices are elevated  however across the street from the hotel is the nicest english language book store with very reasonable prices  also a restaurant on the street is divine but you need reservations     the first item on your itinerary should be a bus tour then use the metro to get around between sights it is very cheap  not real clean but what subway is clean?   the only downside is not seeing all of the city  the taxi way can become costly    enjoy
36  1459750852
we came to watch the six nation rubby match between france and england  disappointing that the french supporters were very disrespectful towards our players but we still beat them!     also stadefrance is an alcohol free stadium  although it has heiniken advertised they don&#39;t make it clear that it is alcohol free lager    had a good time visiting the rest of the sight seeing places
0   1459752417
the metro and bus services are easy to use try using the buses to see paris from above the ground it gives you a  great introduction to the city when eating out try the latin quarter fantastic value for money
34  1468155475
unfortunately the experience with the french wasn&#39;t at all a good one  i even got to the point of asking a waitress what her problem with aussies were   her answer &quot;your either english or american&quot; clearly aussies aren&#39;t the same! so that was disappointing as on a number of occasions our food was thrown on our table ;(  hotel staff were amazing though and there was so much to see that&#39;s why we loved it!
56  1468156547
favourite places to visit were the arc de triomphe and the louvre! we ate out breakfast lunch and dinner as there are loads of fruit stands pastry shops and supermarkets about we walked everywhere during our stay!
77  1468157709
les immeubles et lÃƒÂ  fleuve
93  1468157896
everywhere was easy accessible and cheap beautiful love it lines can be long and you can be waiting around for a while in ques food was amazing
92  1468160281
the eiffel tower was an attraction we enjoyed we&#39;d booked a tour which was 100% worth it for the price we bought it for; the experience wouldn&#39;t have been the same without the tour guide explaining the history of the eiffel tower and the scenery around it also with it lighting up at night it made for a romantic walk by in the evenings we decided to leave our hotels for walks and drinks out another attraction worth going to is the lourve the artwork and interior is huge - a months worth of activity if you were to visit it all - but walking around for the day was enough time to admire as much as we could the people inside we very helpful and the building is signed perfectly to help you find your way surrounding restaurants were also very delicious each allowing a range of foods - this was especially helpful due to the fact i am a fussy eater and there were a large range of places i could eat at     however many sellers on the street and people surrounding the attractions came across rather rude and arrogant to us and many other tourists - especially young couples depending on your accent people seemed to judge you - there were many a time where due to our language and accent we&#39;d be left at the back of restaurants receiving food last and being served last also transport in paris is very hectic signs making it unsure of whether the road was clear or not whilst a horn of a car can be heard at least once every 5 minutes     all in all the experience was romantic and enjoyable - would recommend to others i&#39;d just advice you had pre-booked tickets to events and attractions to avoid long queues and waiting times
13  1468160793
food shopping and shows
24  1468160935
nice if the weather is good but more expensive in general than any other city i have visited i feel like i have paid double for everything
96  1468161033
if not a football fan don&#39;t book for final week!!!
77  1468161955
only here for one concert should have stayed longer
40  1468162135
a lot of sightseeing good metro for transportation
50  1468162863
paris and its people have grown on me with the euro 2016 tournament  still not my favourite city and i cannot understand its romantic perception but certainly better than previous experiences!
93  1468163742
excellent city weather was good
10  1468164027
the best architecture i&#39;ve ever seen
76  1468164969
the tour to normandy was great
36  1468167441
very nice part of paris found a lot of overpriced restaurants try the cafes the food is good and prices not to bad but drink is expensive    i was working so i only had a few hours each day to explore
66  1468167999
there&#39;s just something special about paris  went there just to follow ireland but fell for the place cant wait to get back
15  1468169149
great place great city great food
66  1468169430
our apartment was close to the louvre which has wonderful paintings apart from thr mona lisa and see the apartment of napoleon for some opulence in case you don&#39;t want to make the journey to versailleswe ate locally where they served traditional food the parisians were very kind and helpfulwe felt safe walking around famous sites and along the seinewe did take the train to versailles but it was closed because of a strike getting around on public transport is easysitting in the front of the driverless train is an experience!
21  1468169505
recommend a visit to the opera garnier beautiful old building interesting audio tour
51  1468169727
taxis were expensive and slow the metro was much quicker and cheaper but very hot and sweaty   the bercy arena was great and celine dion was fantastic as ever!  the pavement cafes are interesting if a bit hectic  the eiffel tower is amazing incredible the alain ducasse restaurant in the tower is very nice but extremely expensive a place to go on special occasions only unless you&#39;re minted!  paris is a great place to visit generally but very busy and expensive everywhere  save up for a year or two then go and have some fun
45  1468172240
it is a great city and very friendly looking forward to going back soon
71  1468172417
always great city
44  1468172699
still my favourite city to visit  love the people architecture and culture
29  1468173479
disneyland
43  1468175318
best way to see paris is on foot we walked for miles so get fit before you go loved just meandering through streets taking in atmosphere at different times of day &#47; night lived on bread croissants cheese and pate with kids we didn&#39;t even attempt art galleries or too many museums but they too lived soaking up atmosphere
58  1468175736
everything!!! what&#39;s not to like in paris??? nothing!!!
29  1468176045
best holiday i ever had managed in a short time to see best of paris: tour eiffel louvre museum notre-dame versailles and spent my birthday at disneyland!really the best!paris people were also very friendly
97  1468176384
metro was easy to use and got me close to everywhere i wanted to go   loved musee d&#39;orsay and rodin   less keen on the pickpockets fake taxis&#39;s and gypsy&#39;s  needed to be constantly aware and on guard   security was tight at all major locations but didn&#39;t effect enjoyment and people were processed efficiently and quickly   buy online tickets or get to museums early and the queues weren&#39;t too bad turn up when you feel like it  be prepared to wait
25  1468176612
went to the stunning sacre coeur so beautiful!!  took a trip to top of the eiffel tower well worth the scary (for me at least) lift ride walked miles!! stunning city!!  sit in a roadside cafe and people watch loved every minute!
66  1468176859
eiffel tower was great big bus tour was great value versailles tour saved long line wait lots of smokers on outdoor patios- not good if you don&#39;t like smoke
49  1468177198
paris sights are not to be missed
1   1468177425
great places to be visited with plenty of stuffs to explore but have to be extra careful of pick pocket esp in metro as i was one of the unlucky oneslost my wallet and all ids
18  1468177596
amazing city with a lot to discover doesn&#39;t matter how long you stay it will never be enough for paris
65  1468178681
metro was very easy to use   one price for most journeys    visited the catacoombs off boulevard st michel spooky but interesting but be warned queued for 2 hours sunday morning to get in !!!!    lovely walk back from eiffel tour to timhotel gare de lyon along the bank of the seine rollerbladers doing stunts to 80&#39;s disco music and lots of ordinary people dancing in groups to their own music at various spots along the river bank late into the evening !!
0   1468180026
disappointed with 2 out of 3 mealsone waitress even licked the spoon and then put it in the jam we were given for breakfastshe then proceeded to bang the coffee machine at every opportunity when we asked her to change itthis is not the paris we have loved for years
70  1468180157
everything in paris
96  1468180178
excellent city break vibrant lively atmosphere  good food beautiful buildings so much to see and do - a wonderful energising break!
42  1468180958
what i like most about paris was that the friend who went enjoyed it it was her first time being there that dislike was to traffic and congestion and the inability to get around very good even in the car what would you recommend
26  1468181047
you can get around very easily on foot and enjoy the space between the major landmarks not just the landmarks themselves walk the seine by the musee d&#39;orsay les invalides to tour effiel or on the other side pass hotel de ville le louvre place de la concorde  these are not long walks! save the metro trips for the longer journeys in the city like getting to the arc de triomphe or sacre coeur it is so beautiful watch the people enjoy the way of life picnicing by the seine walking running cycling eating out i strongly recommend vin des pyrenees for a lovely dinner near metro st paul the brasserie bouquet st paul is a great little place too for a lunch or dinner to provide the energy for the walks!you do not need to spend a lot of money anywhere although there are plenty places where you can if you wish! get up early! don&#39;t waste a minute! my children and i am lucky are not adverse to walking anywhere and were more than happy to take in the sights and eat!! disneyland paris is an option for any age but this needs a day devoted to it alone so if your stay in paris is only 3 days then make it for the next longer trip!
14  1468181425
effie tower and shemps
28  1468183308
small room but clean but internet did not work so my son was not so happy but we came for the football so all fine as we did not stay long time room in the back and if you open your window in the morning you are watched by people in some offices so a minus otherwise ok for price and location lg bartel and benoit
4   1468186313
walk along the banks of the seine - such a lively and beautiful city
10  1468187469
amazing city i am a londoner so therefore used to see  history all around but great see another city with so much of it as well things you used to read about when you were young iconic landmarks eiffel tower was 10 times better than what i expected with landmarks like this they tend to be a bit of a letdown b ut this beauty was totally awe inspiring    metro was a doddle great service
51  1468187504
champs elysees  madame ly
39  1468189164
great sightseeing history architecture and art
50  1468189456
happened to be in paris for of the ffrance euro games montmatre is a great area but be prepared for lots of walking!
70  1468190447
every step in paris has some pleasant surprise for the senses waiting in long queues is orderly and relaxing and the museum or gallery is worth the wait!
9   1468190502
what&#39;s not to love
76  1468191335
visiting family in paris is the big plus for us
6   1468191841
paris has plenty of dodgy areas and dodgy citizens however it is far more relaxed than london and there are plenty of bars cafes etc to go which are very reasonably priced
5   1468192270
we live in belgium this was just a one night trip for a concert at the arena  at is is always beautiful!!
23  1468193043
i loved where the hotel was located we had alot of local shops bars restaurants local to it with very decent cheap prices it&#39;s very easy to get around places using metro taxis  but it would help you much better if you could speak french people are very friendly in paris
94  1468194523
paris in the summer is crazy busy there is so much to see and do eating out was amazing if you steer clear of the tourist areas did not get to see everything in 4 days however what we did see was amazing will have to go back and see the rest as we only covered half travelling to the airport in the summer is crazy traffic allocate at least 2 hours from paris via taxi will catch the train next time
9   1468195090
my favorite palce is the bookstore &quot;shakespere and company&quot; felt very relaxed and pleased to just sit in there
12  1468195122
paris is easy to get around  not happy with bus tour as it should give you the opportunity of a full day&#39;s tour starting from the time you get on the bus
43  1468199252
i liked being a flaneur just walking along the seine and looking at the bridges with their statues and the beautiful buildings private and public along the seine  the musee d&#39;orsay and the musee picasso were highlights  we also had an unforgettable meal at restaurant cobea (near the gaÃƒÂ®tÃƒÂ© metro station): perfect food attentive service
12  1468202086
the cafes were a nice place to sit alfresco and sip a drink while you people watch a good time to go is during the summer sales in the end of june because there are sales everywhere!  if travelling by train and bus take a day pass for 7 euros each and its unlimited rides up and down area 1 and 2 bring your own water with you as the water is really expensive in the touristy areas   heading to the eifel tower at night is a must head there when its dark and the lights are all sparkly and its beautiful
45  1468202409
the greatest city in the world
61  1468202576
seeing all the sights was a dream come true but found the whole experience a bit of a let down
49  1468206250
the hop on hop off bus was a blessing we saw all the major attractions in our  own time
68  1468213171
beautiful place and lovely people  5 days is not enough
64  1468215746
we have been to paris a number of times and will keep coming back (the only city we revisit) always offers something different
82  1468216473
the people are cold and rude it was swamped with tourists the airline had a technical difficultly so all the passengers stood in line for 3+ hours to get their new boarding pass and the new flight couldn&#39;t take off until the next morning i am fed up with this city and the people in it
93  1468218394
there is much more to see and explore than the normal top 4 or 5 tourist attractions there are some real interesting alley ways off the beaten track lots of little cafÃƒÂ©&#47;restaurants offering good meals and passing time while soaking up the atmosphere
34  1468218962
stunning sights such as versailles eiffel notre dame etcbucket list stuff the city however is noisy dirty smelly too many people very expensive
9   1468219891
beautiful city plenty to do expensive for dining so do your research on places to eat or you will end up paying Ã‚Â£950 for mocha!!       metro&#47;rer relatively easy to use we bought 2 day ticket and got quite a lot of use out of it you can buy books of 10 but we were on and off tubes
56  1468221558
beautiful charming city i recommend visiting the louvre especially if you are under 26 as it is free then it was very expensive to eat out so choose a place where you can cook your own food the metro is quite affordable 18 euros for a single trip and takes you around the city quickly closes around 030 so take that into consideration when going out
23  1468222276
paris is a great city but the weather was awful we got frozen in the middle of july
22  1468223433
wonderful museums  churches  shops  food and the people   we had an amazing experience even if it drizzled constantly   got to observe le seine reach very high levels   fascinating just roaming through the cobblestone streets   finding adventure along the way
43  1468223502
very friendly people and great transport facilities
31  1468223726
city not very clean but beautifull best to do hop on hop off tour!
43  1468223828
didn&#39;t do much sightseeing i was there for the football (euros)
53  1468225676
we were there for a wedding but also had time to enjoy the euros fanzone
35  1468226199
its an antique city with so much to see i wish i had more time to visit the sites
89  1468226522
lots of sights to see everything walkable but very good metro system very expensive to eat and drink
27  1468226845
on this visit i rediscovered the delights of the bus and tram systems the metro&#39;s excellent if you have limited time to get to places  but if you have more time available and have maybe already visited the &#39;usual&#39; tourist places my recommendation is that you explore the bus network  but be prepared for very slow journeys in some places and at some times traffic can be horrendous my favourite discovery this time was the bus that does a round trip from place pigalle up to the sacre coeur and back again  it&#39;s a tour de force of bus-driving it saves your legs and it gives you fascinating glimpses of the community of montmartre
66  1468227024
musee d orsay  is the best with map is easy to get around  if you use metro or rer
76  1468228872
paris is paris - if you have never been why not? and those that have been will want to go again
33  1468230926
enjoyed eating in the small cafes tucked away in the side streets that you can find for yourselves! :-)  found that the heat was a bit difficult to cope with coming as i do from scotland!
89  1468233274
paris is a beautiful city with lots to see people very friendly and helpful for example with giving directions very good commuter systemgreat restaurants with a wide variety of cuisinei9 certainly would visit again
52  1468233406
never likedid this city   themail are some nice people   but the vibe is not there
14  1468233791
to many good things to say about paris to list here only downside was high military presence in the streets which is a result of increased security following terrorist attacks
86  1468234101
easy to get around paris on the metro works out cheaper if you buy a carte of 10 tickets to use on the metro
15  1468234765
you have to be there at least once in your life
16  1468236658
the people seemed nice and there was a lovely park across the road for walking 5minute walk to the metro and only a few stops to monmart very nice and cheap restaurant jist around the corner for accommodation
80  1468238640
+ culture  - cost
33  1468240125
all the sights are worth seeing the public transport is great especially compared to the uk go and have fun in paris its really a unique city
97  1468240188
the irish bars very close bye restuarants cafe
25  1468241304
a great city with loads to do on many levels!
49  1468242793
good walking shoes are the best for this beautiful city  we did not take any transportation and walking is the best way to see paris  so much to see and do  do not miss the louvre  we ate dinner in the latin district and by notre dame  all our meals were outstanding
63  1468243436
paris leaves in my heart!
90  1468244001
don&#39;t miss the river cruise
73  1468244061
paris is always a good idea
2   1468245122
liked the notre dame cathedral the best it is free entry and it is fabulous also louvre champs elysees and marais district  we spent 3 nights in paris and would have benefited from and extra day  metro service desk officials not always so helpful - but maybe they are not sure themselves  metro automated system is excellent
24  1468246507
i love cities and the varied areas of paris make it interesting  good restaurants
89  1468247288
amazing city
67  1468247295
i enjoyed seeing &#39;la traviata&#39; at the opera bastille - not even necessary to book in advance just turned up and got a ticket on the day of performance (though i wouldn&#39;t recommend this if there are a few of you and you want to sit together!)  i also enjoyed my visit to the parc floral - it&#39;s out near the chateau de vincennes so not too &#39;touristy&#39;
6   1468252291
dinner at the eiffel tower was great our trip to girverny was beautiful easy walking in paris
34  1468254159
walking in the streets of paris is good for your health food is everywhere goodtastyunderground is perfect and reasonable pricetakes you anywhereyou can visit different places but this is almost my 10th visit
54  1468258696
research your routes - i based my movements on pokÃƒÂ©mon go and where pokemons would be at it was a good experience     however we went there during the euro 2016 - not for euro 2016 for travelling thus there were a few roads and attractions which were not accessible
51  1468262902
euro 2016
90  1468266239
eiffel tower and other sights were amazing! traffic is a nightmare!
44  1468269142
the effiel tour takes a lot longer than an hour- plan for 2-3 and more if you include tickets for lunch (which was lovely)
63  1468274929
what else or more can you say about paris - one should go and experience it! it is fascinating! there is so much to see and do so plan for a longer stay!    one recommendation to the louvre management: since every single visitor of the louvre wants to see the mona lisa you should organize a single line (something like the snake lines at the airports) so that everybody will have his&#47;her own time in front of the painting - and not the crowd that we faced where everybody was trying to take a picture of him&#47;herself with the painting!
26  1468275386
cleanliness in paris is not a strong point but there is so much to see everywhere that it makes up for this  the public servants eg the information personnel at the metro are not at all helpful but the general public are great    once you work out the metro ticketing system and the buses it is easy to get around paris at low cost  loved all the usual tourist places but need a guide as much of the things you see do not have english translations available there are numerous beautiful gardens to walk in at no cost but take good walking shoes as the streets are hard on your feet
13  1468275572
i lived in this city and i always love coming back !history culture good food what else do you need?
92  1468276210
cycle lanes everywhere including along oucq canal amazing to get out of paris with views and no hassle with traffic
47  1468280055
stimulating and playful atmoshere
51  1467354436
euros atmosphere was fantastic got a real sense of french pride
35  1467355926
paris is the vary beautiful city and have a lot of places of interest you can use the metro and bus for your transmission easily
50  1467357261
there is not too much unknown to say about paris just bring loads of euros as it seams to be a lot dearer than other euro capitals
62  1467358855
nice city to be lot of place to visit apart from effile tower  beware of pick pockets plan in advance and buy metro pass its worth food is bit expensive so go for local road side shops they give good food in somewhat reasonable rate
72  1467358891
lovely city  everything woow i&#39;ll definitely come back tour eiffel  champs elysee  moulin rouge  louvre and lovely disneyland
71  1467358981
very nice city
5   1467359311
we were fortunate to have good weather for our short break first trip to paris and what an amazing city!      two full days in paris wasn&#39;t enough to see all of the magnificent museums and palaces  we used big red bus tours to visit the tourist sights  a 2 day ticket with river cruise was just right  the only downside was the company routes are one way across paris so a bit of planning before hand is recommended to make sure you don&#39;t spend too much time on the bus travelling from one place to another  having said that the bus company has staff at each stop who were very helpful and friendly  the is another bus company called open tour who looked like they started services earlier and allowed you to hop on and off at request  we didn&#39;t use them but we&#39;d recommend having a look at both before buying  either way they&#39;re the best way to visit the attractions with audio tours earphones provided ponchos if it&#39;s raining and a guide map included    we found using the metro was best first thing in the morning to reach eiffel tower with shorter queues before the tourist buses started to arrive at 10am  just be aware that the traffic in paris is crazy so the buses do take a little while to move around  we stayed near to notre dam cathedral and the eiffel tower is 4 stops along on the rer route 10 no changes required 2 euro single each and takes about 10 minutes  very easy and again the metro staff were very helpful and friendly      plenty of places to eat drink and enjoy paris  really superb!    two final pieces of advice from our experience  firstly check the attraction you&#39;re planning to visit is open  on our trip the eiffel tower was closed for one day due to a national strike  and secondly just check ahead for access to some attractions if you&#39;re needing assistance  we visited arc de triumph to find the lift was out of service  it&#39;s a long walk up!
7   1467360195
the best bits were out of the main tourist areas not because there were less people but because there were no street sellers they really should do something about this    we used the metro &amp; rer - really good value and level of service    we had a lovely day at versailles - fantastic - you need a whole day to see it all and do the garden and out lying parts first and then the palace
expected output
15
59
31
90
28
95
3
2
27
54
62
22
33
23
19
33
19
19
 */