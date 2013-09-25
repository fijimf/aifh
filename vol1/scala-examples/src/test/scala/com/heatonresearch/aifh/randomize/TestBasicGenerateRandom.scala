/*
 * Artificial Intelligence for Humans
 * Volume 1: Fundamental Algorithms
 * Scala Version
 * http://www.aifh.org
 * http://www.jeffheaton.com
 *
 * Code repository:
 * https://github.com/jeffheaton/aifh

 * Copyright 2013 by Jeff Heaton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For more information on Heaton Research copyrights, licenses
 * and trademarks visit:
 * http://www.heatonresearch.com/copyright
 */
package com.heatonresearch.aifh.randomize

import com.heatonresearch.aifh.AIFH
import org.scalatest.Suite
import org.scalatest.matchers.ShouldMatchers

/**
 * Test basic random.
 */
object TestBasicGenerateRandom {
  val BOOLEAN_TEST = List(true, false, false, false, false, false, false, true, true, true, false, false,
    true, false, true, true, true, true, true, false, false, true, false, false, false, false, true, true, false, true, true,
    true, true, false, false, true, false, true, false, true, true, false, true, false, false, true, true, false, true, true,
    false, false, false, false, true, true, true, true, false, false, true, false, false, false, false, true, false, false,
    false, false, true, false, true, false, false, false, false, true, false, false, false, true, false, true, true, false,
    false, false, true, false, true, false, false, true, true, true, true, true, false, false)
  val DOUBLE_RANGE_TEST = List(0.4617563814065817, -0.17983837701559668, -0.5845703173805659,
    -0.33456588808097765, 0.9355118188482414, -0.9877656354684774, 0.9274095940464153, 0.8797307775638197,
    0.8943898353263877, 0.8741642977919393, -0.2056513156305888, -0.3049639415937795, -0.41188593599192647,
    0.012967254652470173, -0.7680658239346845, 0.5410717601583555, 0.31978541738683997, -0.6865062188603075,
    -0.24359590935788944, -0.7204746341924977, 0.38989595920498377, 0.6104555429474274, -0.9899496480150949,
    0.04627031157666606, 0.4879689724746332, -0.7159545935681477, -0.03654339684880403, 0.08910961778734738,
    0.154200522748553, -0.5901729084828768, 0.2467276212633316, -0.6305858194441027, -0.9786311780355423,
    -0.6779133532565955, -0.6438903067256505, 0.08079412956759291, 0.9476680198374003, -0.5091468928374088,
    -0.21095818741155647, -0.5647957501846304, -0.13598624021292172, -0.5336884104973332, 0.7798175033107542,
    -0.9233462133614492, 0.18475848772587744, 0.31034720625883283, -0.7603219153916208, 0.3049534137919063,
    0.9686456013385898, -0.5865249852789853, -0.2507009994120597, -0.07330042486104849, -0.3327795776506606,
    -0.11357509936546184, 0.008271133409147868, 0.9979616295358575, 0.2608097278457078, 0.8191680711780485,
    0.015319832139896183, -0.017097588876774816, -0.14243164250759865, -0.3838141695830404, 0.43464480324445387,
    0.9248291854674928, -0.5810145330728713, -0.6547023581719895, 0.09790503386131122, 0.11093676417592313,
    0.17563165780266687, 0.5783813156701916, 0.39801324486896106, -0.590728895588629, -0.49018109740036864,
    0.5558327190390557, -0.5499552279832491, 0.9662063696674061, 0.607237212030854, 0.6726298955126573,
    -0.6737929452233706, 0.27499485971881277, -0.9824791653625367, 0.2623797766230498, -0.584044041914755,
    0.7607145698140847, 0.41408845210793044, 0.44674329243160504, 0.018985839340738497, 0.9742042761925997,
    -0.6935075343674779, 0.4211495502966689, 0.6479617509063196, -0.7526575429941516, 0.23390277349257982, -0.03627528947880365, -0.8004994373485674, 0.23429177972286364, -0.9437592540976656, 0.18287821190927622, -0.8539479595723121, -0.8519041454533085)
  val DOUBLE_TEST = List(0.7308781907032909, 0.41008081149220166, 0.20771484130971707, 0.3327170559595112, 0.9677559094241207, 0.006117182265761301, 0.9637047970232077, 0.9398653887819098, 0.9471949176631939, 0.9370821488959696, 0.3971743421847056, 0.34751802920311026, 0.29405703200403677, 0.5064836273262351, 0.11596708803265776, 0.7705358800791777, 0.65989270869342, 0.15674689056984625, 0.3782020453210553, 0.13976268290375116, 0.6949479796024919, 0.8052277714737137, 0.005025175992452557, 0.523135155788333, 0.7439844862373166, 0.14202270321592614, 0.481728301575598, 0.5445548088936737, 0.5771002613742765, 0.20491354575856158, 0.6233638106316658, 0.18470709027794863, 0.010684410982228831, 0.16104332337170224, 0.17805484663717475, 0.5403970647837965, 0.9738340099187002, 0.24542655358129561, 0.39452090629422176, 0.21760212490768482, 0.43200687989353914, 0.2331557947513334, 0.8899087516553771, 0.03832689331927541, 0.5923792438629387, 0.6551736031294164, 0.11983904230418962, 0.6524767068959532, 0.9843228006692949, 0.20673750736050733, 0.37464950029397015, 0.46334978756947576, 0.3336102111746697, 0.4432124503172691, 0.5041355667045739, 0.9989808147679288, 0.6304048639228539, 0.9095840355890242, 0.5076599160699481, 0.4914512055616126, 0.4287841787462007, 0.3080929152084798, 0.7173224016222269, 0.9624145927337464, 0.20949273346356434, 0.17264882091400524, 0.5489525169306556, 0.5554683820879616, 0.5878158289013334, 0.7891906578350958, 0.6990066224344805, 0.20463555220568552, 0.2549094512998157, 0.7779163595195279, 0.22502238600837543, 0.9831031848337031, 0.803618606015427, 0.8363149477563286, 0.16310352738831468, 0.6374974298594064, 0.008760417318731673, 0.6311898883115249, 0.2079779790426225, 0.8803572849070423, 0.7070442260539652, 0.7233716462158025, 0.5094929196703692, 0.9871021380962999, 0.15324623281626104, 0.7105747751483344, 0.8239808754531598, 0.12367122850292422, 0.6169513867462899, 0.4818623552605982, 0.09975028132571628, 0.6171458898614318, 0.028120372951167205, 0.5914391059546381, 0.07302602021384397, 0.07404792727334575)
  val LONG_TEST = List(-4964420948893066024l, 7564655870752979346l, 3831662765844904176l, 6137546356583794141l, -594798593157429144l, 112842269129291794l, -669528114487223426l, -1109287713991315740l, -974081879987450628l, -1160629452687687109l, 7326573195622447256l, 6410576364588137014l, 5424394867226112926l, -9103770306483490189l, 2139215297105423308l, -4232865876030345843l, -6273872167485304708l, 2891469594365336806l, 6976596177944619528l, 2578166436595196069l, -5627216606837767319l, -3592913410653813758l, 92698085241473569l, -8796603504740353600l, -4722652817683412901l, 2619856624980352251l, 8886318912347348303l, -8401480976436315613l, -7801123389691242517l, 3779987867844568136l, -6947711303906420817l, 3407244680303549079l, 197092594700490712l, 2970725011242582564l, 3284532136690698432l, -8478177725643278359l, -482677293272704124l, 4527320925905780494l, 7277626163180921831l, 4014050679668805482l, 7969120158891947125l, 4300965142756182089l, -2030825140507191061l, 707006413279611759l, -7519275600551226667l, -6360924135797636003l, 2210640064016022649l, -6410673298797731886l, -289193436830779917l, 3813634057487595412l, 6911063436971917473l, 8547294963617019503l, 6154022364946197696l, 8175826803456981118l, -9147084144055124649l, -18800628192384088l, -6817826759444601261l, -1667880028869348243l, -9082071447080613645l, 9065674809775364834l, 7909671975457870438l, 5683311091615826937l, -5214481407826501455l, -693328208225879290l, 3864458965704708566l, 3184808690151788414l, -8320357513071910606l, -8200160751728555263l, -7603456060161050842l, -3888746125786119271l, -5552347832537805063l, 3774859742041214532l, 4702249276633814781l, -4096719924219374161l, 4150930343758163695l, -311691390498039484l, -3622597253628401501l, -3019456038419834778l, 3008729024856518368l, -6686992125460025861l, 161601140914943624l, -6803345800057020374l, 3836516331752709628l, -2207018395996362651l, -5404080405594186050l, -5102892484113533015l, -9048258330105186985l, -237923595412718844l, 2826893961496978298l, -5338953178777934760l, -3246979880425410455l, 2281331448982092637l, -7065999876450923625l, 8888791547312749291l, 1840067945267344782l, -7062411921403462023l, 518729297779020562l, -7536618281581788192l, 1347092278477147782l, 1365943130551420261l)
  val FLOAT_TEST = List(0.7308781743049622f, 0.10047316551208496f, 0.41008079051971436f, 0.4074397683143616f, 0.20771479606628418f, 0.03623533248901367f, 0.33271700143814087f, 0.6588671803474426f, 0.9677558541297913f, 0.7107396125793457f, 0.0061171650886535645f, 0.15273618698120117f, 0.9637047648429871f, 0.15957802534103394f, 0.9398653507232666f, 0.5540722608566284f, 0.9471948742866516f, 0.9109504222869873f, 0.9370821118354797f, 0.4870873689651489f, 0.39717429876327515f, 0.9139628410339355f, 0.34751802682876587f, 0.1593395471572876f, 0.2940570116043091f, 0.3690025210380554f, 0.5064836144447327f, 0.8644629716873169f, 0.1159670352935791f, 0.5392596125602722f, 0.7705358266830444f, 0.5833538174629211f, 0.6598926782608032f, 0.042298316955566406f, 0.15674686431884766f, 0.7616746425628662f, 0.37820202112197876f, 0.6239725351333618f, 0.13976263999938965f, 0.8792629241943359f, 0.6949479579925537f, 0.4502183794975281f, 0.8052277565002441f, 0.0048525333404541016f, 0.005025148391723633f, 0.8522535562515259f, 0.5231351256370544f, 0.023418009281158447f, 0.7439844608306885f, 0.705009937286377f, 0.14202266931533813f, 0.27502989768981934f, 0.4817282557487488f, 0.07538777589797974f, 0.5445547699928284f, 0.6105915307998657f, 0.5771002173423767f, 0.9549307227134705f, 0.20491349697113037f, 0.27406907081604004f, 0.6233637928962708f, 0.19020217657089233f, 0.18470704555511475f, 0.0012985467910766602f, 0.010684370994567871f, 0.6835264563560486f, 0.16104328632354736f, 0.4862595796585083f, 0.1780548095703125f, 0.4875149726867676f, 0.540397047996521f, 0.12657493352890015f, 0.9738339781761169f, 0.13020867109298706f, 0.24542653560638428f, 0.20627588033676147f, 0.3945208787918091f, 0.845655620098114f, 0.21760207414627075f, 0.40654081106185913f, 0.4320068359375f, 0.9498398303985596f, 0.23315578699111938f, 0.5207791328430176f, 0.8899087309837341f, 0.3872504234313965f, 0.03832685947418213f, 0.27130573987960815f, 0.5923792123794556f, 0.11282074451446533f, 0.6551735997200012f, 0.22880196571350098f, 0.11983901262283325f, 0.9918820858001709f, 0.6524766683578491f, 0.5862483382225037f, 0.9843227863311768f, 0.9622147679328918f, 0.2067374587059021f, 0.26515525579452515f)
  val GAUSSIAN_TEST = List(1.561581040188955, -0.6081826070068602, -1.0912278829447088, -0.6245401364066232, -1.1182832102556484, -1.6583217791337177, -1.8821643777572246, 0.059255494425680996, -0.4084113286637019, 0.28770950299107917, 0.4452322425599013, -0.9558118873968129, -0.33514653612132894, -0.9912505454192135, 0.6113509486615071, 0.9571850297129593, -0.1896357718231955, 0.008863588431931045, 0.42649995328496526, -0.6257664276530307, -1.160868794354026, 2.8307323206471837, 0.355468389677467, -1.3604870442090895, 0.4550292057260641, -1.1629624729268742, -1.3049338775280348, 0.16374061804763837, -0.4977911061850049, -1.332729981721464, -0.3813989234287897, -1.4968292739868196, 1.0324407718418314, 1.7342375612221888, -0.8289580667590492, 0.33248231733305234, -2.2241882374671493, -0.650312296957864, -1.9350887207828409, -0.6604308332123978, 7.424089993490053E-4, 0.0895760784069797, 0.23592566770842976, 0.7410106047594965, 2.5928475818421197, -2.8937289631932432, -0.6575765336955727, -1.7719882097420567, -0.48440511293298283, -0.5458403390884142, 1.8293521912945987, 2.0728496241351397, 0.41231669499983764, 1.3578205405014976, 0.6509150369172672, -0.9660842342238222, -0.7240969451346598, 0.8210777118835697, 0.42064995400824456, 0.4659492682697997, -1.043880110171755, 0.42603839427967904, -0.2490190380923727, 0.3243461055041554, 0.9572421001282879, 1.0327298075774218, 0.006276956837860862, 0.32208416405374324, -0.7815729418130682, 0.474629440426818, 0.1081785845840964, -0.12565776847748328, 2.372443477242464, -0.3679352434519125, -0.8175796117309211, 0.23929083939400242, -0.39000312238449375, 0.0755733767388626, 0.9516722405507335, -1.163563877547742, 0.32014658155942527, 1.0764795097470736, -1.692503450095845, 1.142013788928328, -0.3753962887120263, 0.6797051305241064, 1.5609514050289766, -0.4906045694959899, -0.7712152980645192, -1.4202394909483873, 0.3320680589211738, 0.5587993827731479, -0.5880209601322698, -0.054510905213974135, -0.6599800758329508, 0.13003910434910687, 0.7850424301182043, 1.8901983918586256, 0.34535462831405195, -0.2149678470259206)
  val INT_TEST = List(-1155869325, 431529176, 1761283695, 1749940626, 892128508, 155629808, 1429008869, -1465154083, -138487339, -1242363800, 26273138, 655996946, -155886662, 685382526, -258276172, -1915244828, -226796111, -382464772, -270230103, 2092024379, 1705850753, -369526632, 1492578621, 684358198, 1262965348, 1584853918, -2119636700, -582126989, 498074875, -1978864692, -985540886, -1789481587, -1460749695, 181670012, 673222727, -1023599386, 1624365379, -1615025656, 600276151, -518561627, -1310188465, 1933673321, -836540342, 20841474, 21582955, -634566111, -2048118856, 100579776, -1099578295, -1266972581, 609982904, 1181244667, 2069007352, 323788111, -1956122223, -1672496605, -1816340580, -193570837, 880097008, 1177117768, -1617640095, 816912303, 793310972, 5577367, 45889196, -1359243304, 691675816, 2088469028, 764739731, 2093861056, -1973979577, 543635433, -112382065, 559242116, 1054099045, 885948174, 1694454384, -662903833, 934594003, 1746079594, 1855455376, -215436171, 1001396483, -2058237879, -472838325, 1663228139, 164612758, 1165249391, -1750717778, 484561621, -1481018061, 982697053, 514704749, -34866055, -1492601190, -1777049646, -67333094, -162286093, 887930872, 1138833300)
  val INT_RANGE_TEST = List(7, 4, 2, 3, 9, 0, 9, 9, 9, 9, 3, 3, 2, 5, 1, 7, 6, 1, 3, 1, 6, 8, 0, 5, 7, 1, 4, 5, 5, 2, 6, 1, 0, 1, 1, 5, 9, 2, 3, 2, 4, 2, 8, 0, 5, 6, 1, 6, 9, 2, 3, 4, 3, 4, 5, 9, 6, 9, 5, 4, 4, 3, 7, 9, 2, 1, 5, 5, 5, 7, 6, 2, 2, 7, 2, 9, 8, 8, 1, 6, 0, 6, 2, 8, 7, 7, 5, 9, 1, 7, 8, 1, 6, 4, 0, 6, 0, 5, 0, 0)
}

class TestBasicGenerateRandom extends Suite with ShouldMatchers {
  import TestBasicGenerateRandom._

  def testGenerateBoolean() {
    val rnd: BasicGenerateRandom = new BasicGenerateRandom(1)
    for (aBOOLEAN_TEST <- BOOLEAN_TEST) {
      val g: Boolean = rnd.nextBoolean
      assert(g === aBOOLEAN_TEST)
    }
  }

  def testDoubleRange() {
    val rnd: BasicGenerateRandom = new BasicGenerateRandom(1)
    for (aDOUBLE_RANGE_TEST <- DOUBLE_RANGE_TEST) {
      val g = rnd.nextDouble(-1, 1)
      g should be (aDOUBLE_RANGE_TEST plusOrMinus AIFH.DEFAULT_PRECISION)
    }
  }

  def testDouble() {
    val rnd = new BasicGenerateRandom(1)
    for (aDOUBLE_TEST <- DOUBLE_TEST) {
      val g: Double = rnd.nextDouble()
      g should be (aDOUBLE_TEST plusOrMinus AIFH.DEFAULT_PRECISION)
    }
  }

  def testLong() {
    val rnd = new BasicGenerateRandom(1)
    for (aLONG_TEST <- LONG_TEST) {
      val l: Long = rnd.nextLong
      assert(l === aLONG_TEST)
    }
  }

  def testFloat() {
    val rnd = new BasicGenerateRandom(1)
    for (aFLOAT_TEST <- FLOAT_TEST) {
      val l: Float = rnd.nextFloat
      l should be (aFLOAT_TEST plusOrMinus AIFH.DEFAULT_PRECISION.asInstanceOf[Float])
    }
  }

  def testGaussianFloat() {
    val rnd = new BasicGenerateRandom(1)
    for (aGAUSSIAN_TEST <- GAUSSIAN_TEST) {
      val g: Double = rnd.nextGaussian
      g should be (aGAUSSIAN_TEST plusOrMinus AIFH.DEFAULT_PRECISION)
    }
  }

  def testInt() {
    val rnd = new BasicGenerateRandom(1)
    for (aINT_TEST <- INT_TEST) {
      val g : Int = rnd.nextInt
      assert(g === aINT_TEST)
    }
  }

  def testIntRange() {
    val rnd = new BasicGenerateRandom(1)
    for (aINT_RANGE_TEST <- INT_RANGE_TEST) {
      val g : Int = rnd.nextInt(0, 10)
      assert(g === aINT_RANGE_TEST)
    }
  }
}