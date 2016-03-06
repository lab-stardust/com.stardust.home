package com.stardust.home;

import java.util.Locale;
import java.util.TimeZone;

import android.graphics.PointF;

/**
 * Home Planetarium
 * Location class
 * @author Stardust Laboratory
 * @version 1.0
 */
public class Location {
	private static final String[] LOCATION_ID = { "Europe/Andorra", "Asia/Dubai", "Asia/Kabul", "America/Antigua", "America/Anguilla", "Europe/Tirane", "Asia/Yerevan", "Africa/Luanda",
			"Antarctica/McMurdo", "Antarctica/South_Pole", "Antarctica/Rothera", "Antarctica/Palmer", "Antarctica/Mawson", "Antarctica/Davis", "Antarctica/Casey", "Antarctica/Vostok",
			"Antarctica/DumontDUrville", "Antarctica/Syowa", "Antarctica/Macquarie", "America/Argentina/Buenos_Aires", "America/Argentina/Cordoba", "America/Argentina/Salta",
			"America/Argentina/Jujuy", "America/Argentina/Tucuman", "America/Argentina/Catamarca", "America/Argentina/La_Rioja", "America/Argentina/San_Juan", "America/Argentina/Mendoza",
			"America/Argentina/San_Luis", "America/Argentina/Rio_Gallegos", "America/Argentina/Ushuaia", "Pacific/Pago_Pago", "Europe/Vienna", "Australia/Lord_Howe", "Australia/Hobart",
			"Australia/Currie", "Australia/Melbourne", "Australia/Sydney", "Australia/Broken_Hill", "Australia/Brisbane", "Australia/Lindeman", "Australia/Adelaide", "Australia/Darwin",
			"Australia/Perth", "Australia/Eucla", "America/Aruba", "Europe/Mariehamn", "Asia/Baku", "Europe/Sarajevo", "America/Barbados", "Asia/Dhaka", "Europe/Brussels", "Africa/Ouagadougou",
			"Europe/Sofia", "Asia/Bahrain", "Africa/Bujumbura", "Africa/Porto", "America/St_Barthelemy", "Atlantic/Bermuda", "Asia/Brunei", "America/La_Paz", "America/Kralendijk", "America/Noronha",
			"America/Belem", "America/Fortaleza", "America/Recife", "America/Araguaina", "America/Maceio", "America/Bahia", "America/Sao_Paulo", "America/Campo_Grande", "America/Cuiaba",
			"America/Santarem", "America/Porto_Velho", "America/Boa_Vista", "America/Manaus", "America/Eirunepe", "America/Rio_Branco", "America/Nassau", "Asia/Thimphu", "Africa/Gaborone",
			"Europe/Minsk", "America/Belize", "America/St_Johns", "America/Halifax", "America/Glace_Bay", "America/Moncton", "America/Goose_Bay", "America/Blanc", "America/Montreal",
			"America/Toronto", "America/Nipigon", "America/Thunder_Bay", "America/Iqaluit", "America/Pangnirtung", "America/Resolute", "America/Atikokan", "America/Rankin_Inlet", "America/Winnipeg",
			"America/Rainy_River", "America/Regina", "America/Swift_Current", "America/Edmonton", "America/Cambridge_Bay", "America/Yellowknife", "America/Inuvik", "America/Creston",
			"America/Dawson_Creek", "America/Vancouver", "America/Whitehorse", "America/Dawson", "Indian/Cocos", "Africa/Kinshasa", "Africa/Lubumbashi", "Africa/Bangui", "Africa/Brazzaville",
			"Europe/Zurich", "Africa/Abidjan", "Pacific/Rarotonga", "America/Santiago", "Pacific/Easter", "Africa/Douala", "Asia/Shanghai", "Asia/Harbin", "Asia/Chongqing", "Asia/Urumqi",
			"Asia/Kashgar", "America/Bogota", "America/Costa_Rica", "America/Havana", "Atlantic/Cape_Verde", "America/Curacao", "Indian/Christmas", "Asia/Nicosia", "Europe/Prague", "Europe/Berlin",
			"Africa/Djibouti", "Europe/Copenhagen", "America/Dominica", "America/Santo_Domingo", "Africa/Algiers", "America/Guayaquil", "Pacific/Galapagos", "Europe/Tallinn", "Africa/Cairo",
			"Africa/El_Aaiun", "Africa/Asmara", "Europe/Madrid", "Africa/Ceuta", "Atlantic/Canary", "Africa/Addis_Ababa", "Europe/Helsinki", "Pacific/Fiji", "Atlantic/Stanley", "Pacific/Chuuk",
			"Pacific/Pohnpei", "Pacific/Kosrae", "Atlantic/Faroe", "Europe/Paris", "Africa/Libreville", "Europe/London", "America/Grenada", "Asia/Tbilisi", "America/Cayenne", "Europe/Guernsey",
			"Africa/Accra", "Europe/Gibraltar", "America/Godthab", "America/Danmarkshavn", "America/Scoresbysund", "America/Thule", "Africa/Banjul", "Africa/Conakry", "America/Guadeloupe",
			"Africa/Malabo", "Europe/Athens", "Atlantic/South_Georgia", "America/Guatemala", "Pacific/Guam", "Africa/Bissau", "America/Guyana", "Asia/Hong_Kong", "America/Tegucigalpa",
			"Europe/Zagreb", "America/Port", "Europe/Budapest", "Asia/Jakarta", "Asia/Pontianak", "Asia/Makassar", "Asia/Jayapura", "Europe/Dublin", "Asia/Jerusalem", "Europe/Isle_of_Man",
			"Asia/Kolkata", "Indian/Chagos", "Asia/Baghdad", "Asia/Tehran", "Atlantic/Reykjavik", "Europe/Rome", "Europe/Jersey", "America/Jamaica", "Asia/Amman", "Asia/Tokyo", "Africa/Nairobi",
			"Asia/Bishkek", "Asia/Phnom_Penh", "Pacific/Tarawa", "Pacific/Enderbury", "Pacific/Kiritimati", "Indian/Comoro", "America/St_Kitts", "Asia/Pyongyang", "Asia/Seoul", "Asia/Kuwait",
			"America/Cayman", "Asia/Almaty", "Asia/Qyzylorda", "Asia/Aqtobe", "Asia/Aqtau", "Asia/Oral", "Asia/Vientiane", "Asia/Beirut", "America/St_Lucia", "Europe/Vaduz", "Asia/Colombo",
			"Africa/Monrovia", "Africa/Maseru", "Europe/Vilnius", "Europe/Luxembourg", "Europe/Riga", "Africa/Tripoli", "Africa/Casablanca", "Europe/Monaco", "Europe/Chisinau", "Europe/Podgorica",
			"America/Marigot", "Indian/Antananarivo", "Pacific/Majuro", "Pacific/Kwajalein", "Europe/Skopje", "Africa/Bamako", "Asia/Rangoon", "Asia/Ulaanbaatar", "Asia/Hovd", "Asia/Choibalsan",
			"Asia/Macau", "Pacific/Saipan", "America/Martinique", "Africa/Nouakchott", "America/Montserrat", "Europe/Malta", "Indian/Mauritius", "Indian/Maldives", "Africa/Blantyre",
			"America/Mexico_City", "America/Cancun", "America/Merida", "America/Monterrey", "America/Matamoros", "America/Mazatlan", "America/Chihuahua", "America/Ojinaga", "America/Hermosillo",
			"America/Tijuana", "America/Santa_Isabel", "America/Bahia_Banderas", "Asia/Kuala_Lumpur", "Asia/Kuching", "Africa/Maputo", "Africa/Windhoek", "Pacific/Noumea", "Africa/Niamey",
			"Pacific/Norfolk", "Africa/Lagos", "America/Managua", "Europe/Amsterdam", "Europe/Oslo", "Asia/Kathmandu", "Pacific/Nauru", "Pacific/Niue", "Pacific/Auckland", "Pacific/Chatham",
			"Asia/Muscat", "America/Panama", "America/Lima", "Pacific/Tahiti", "Pacific/Marquesas", "Pacific/Gambier", "Pacific/Port_Moresby", "Asia/Manila", "Asia/Karachi", "Europe/Warsaw",
			"America/Miquelon", "Pacific/Pitcairn", "America/Puerto_Rico", "Asia/Gaza", "Asia/Hebron", "Europe/Lisbon", "Atlantic/Madeira", "Atlantic/Azores", "Pacific/Palau", "America/Asuncion",
			"Asia/Qatar", "Indian/Reunion", "Europe/Bucharest", "Europe/Belgrade", "Europe/Kaliningrad", "Europe/Moscow", "Europe/Volgograd", "Europe/Samara", "Asia/Yekaterinburg", "Asia/Omsk",
			"Asia/Novosibirsk", "Asia/Novokuznetsk", "Asia/Krasnoyarsk", "Asia/Irkutsk", "Asia/Yakutsk", "Asia/Vladivostok", "Asia/Sakhalin", "Asia/Magadan", "Asia/Kamchatka", "Asia/Anadyr",
			"Africa/Kigali", "Asia/Riyadh", "Pacific/Guadalcanal", "Indian/Mahe", "Africa/Khartoum", "Europe/Stockholm", "Asia/Singapore", "Atlantic/St_Helena", "Europe/Ljubljana",
			"Arctic/Longyearbyen", "Europe/Bratislava", "Africa/Freetown", "Europe/San_Marino", "Africa/Dakar", "Africa/Mogadishu", "America/Paramaribo", "Africa/Juba", "Africa/Sao_Tome",
			"America/El_Salvador", "America/Lower_Princes", "Asia/Damascus", "Africa/Mbabane", "America/Grand_Turk", "Africa/Ndjamena", "Indian/Kerguelen", "Africa/Lome", "Asia/Bangkok",
			"Asia/Dushanbe", "Pacific/Fakaofo", "Asia/Dili", "Asia/Ashgabat", "Africa/Tunis", "Pacific/Tongatapu", "Europe/Istanbul", "America/Port_of_Spain", "Pacific/Funafuti", "Asia/Taipei",
			"Africa/Dar_es_Salaam", "Europe/Kiev", "Europe/Uzhgorod", "Europe/Zaporozhye", "Europe/Simferopol", "Africa/Kampala", "Pacific/Johnston", "Pacific/Midway", "Pacific/Wake",
			"America/New_York", "America/Detroit", "America/Kentucky/Louisville", "America/Kentucky/Monticello", "America/Indiana/Indianapolis", "America/Indiana/Vincennes",
			"America/Indiana/Winamac", "America/Indiana/Marengo", "America/Indiana/Petersburg", "America/Indiana/Vevay", "America/Chicago", "America/Indiana/Tell_City", "America/Indiana/Knox",
			"America/Menominee", "America/North_Dakota/Center", "America/North_Dakota/New_Salem", "America/North_Dakota/Beulah", "America/Denver", "America/Boise", "America/Shiprock",
			"America/Phoenix", "America/Los_Angeles", "America/Anchorage", "America/Juneau", "America/Sitka", "America/Yakutat", "America/Nome", "America/Adak", "America/Metlakatla",
			"Pacific/Honolulu", "America/Montevideo", "Asia/Samarkand", "Asia/Tashkent", "Europe/Vatican", "America/St_Vincent", "America/Caracas", "America/Tortola", "America/St_Thomas",
			"Asia/Ho_Chi_Minh", "Pacific/Efate", "Pacific/Wallis", "Pacific/Apia", "Asia/Aden", "Indian/Mayotte", "Africa/Johannesburg", "Africa/Lusaka", "Africa/Harare" };
	private static final short[] LOCATION_LATITUDE_LIST = { 425, 253, 345, 171, 182, 413, 402, -88, -778, -900, -676, -648, -676, -686, -663, -784, -667, -690, -545, -346, -314, -248, -242, -268,
			-285, -294, -315, -329, -333, -516, -548, -143, 482, -316, -429, -399, -378, -339, -320, -275, -203, -349, -125, -320, -317, 125, 601, 404, 439, 131, 237, 508, 124, 427, 264, -34, 65,
			179, 323, 49, -165, 122, -39, -15, -37, -81, -72, -97, -130, -235, -205, -156, -24, -88, 28, -31, -67, -100, 251, 275, -247, 539, 175, 476, 447, 462, 461, 533, 514, 455, 437, 490, 484,
			637, 661, 747, 488, 628, 499, 487, 504, 503, 536, 691, 625, 683, 491, 598, 493, 607, 641, -122, -43, -117, 44, -43, 474, 53, -212, -335, -272, 41, 312, 458, 296, 438, 395, 46, 99, 231,
			149, 122, -104, 352, 501, 525, 116, 557, 153, 185, 368, -22, 9, 594, 301, 272, 153, 404, 359, 281, 90, 602, -181, -517, 74, 70, 53, 620, 489, 4, 515, 121, 417, 49, 495, 56, 361, 642, 768,
			705, 766, 135, 95, 162, 38, 380, -543, 146, 135, 119, 68, 223, 141, 458, 185, 475, -62, 0, -51, -25, 533, 318, 542, 225, -73, 334, 357, 642, 419, 492, 180, 320, 357, -13, 429, 116, 14,
			-31, 19, -117, 173, 390, 376, 293, 193, 433, 448, 503, 445, 512, 180, 339, 140, 472, 69, 63, -295, 547, 496, 570, 329, 337, 437, 470, 424, 181, -189, 72, 91, 420, 127, 168, 479, 480, 481,
			222, 152, 146, 181, 167, 359, -202, 42, -158, 194, 211, 210, 257, 258, 232, 286, 296, 291, 325, 303, 208, 32, 16, -260, -226, -223, 135, -291, 65, 122, 524, 599, 277, 5, -190, -369, -440,
			236, 90, -121, -175, -90, -231, -95, 146, 249, 523, 471, -251, 185, 315, 315, 387, 326, 377, 73, -253, 253, -209, 444, 448, 547, 558, 487, 532, 569, 550, 550, 538, 560, 523, 620, 432,
			470, 596, 530, 648, -20, 246, -95, -47, 156, 593, 13, -159, 461, 780, 482, 85, 439, 147, 21, 58, 49, 3, 137, 181, 335, -263, 215, 121, -494, 61, 138, 386, -94, -86, 380, 368, -212, 410,
			107, -85, 251, -68, 504, 486, 478, 450, 3, 168, 282, 193, 407, 423, 383, 368, 398, 387, 411, 384, 385, 387, 419, 380, 413, 451, 471, 468, 473, 397, 436, 368, 334, 341, 612, 583, 572, 595,
			645, 519, 551, 213, -349, 397, 413, 419, 132, 105, 185, 184, 108, -177, -133, -138, 128, -128, -263, -154, -178 };
	private static final short[] LOCATION_LONGITUDE_LIST = { -15, -553, -692, 618, 631, -198, -445, -132, -1666, 0, 681, 641, -629, -780, -1105, -1069, -1400, -396, -1590, 585, 642, 654, 653, 652,
			658, 669, 685, 688, 664, 692, 683, 1707, -163, -1591, -1473, -1439, -1450, -1512, -1415, -1530, -1490, -1386, -1308, -1159, -1289, 700, -200, -499, -184, 596, -904, -43, 15, -233, -506,
			-294, -26, 629, 648, -1149, 682, 683, 324, 485, 385, 349, 482, 357, 385, 466, 546, 561, 549, 639, 607, 600, 699, 678, 774, -897, -259, -276, 882, 527, 636, 600, 648, 604, 571, 736, 794,
			883, 893, 685, 657, 948, 916, 921, 972, 946, 1047, 1078, 1135, 1051, 1144, 1337, 1165, 1202, 1231, 1351, 1394, -969, -153, -275, -186, -153, -85, 40, 1598, 707, 1094, -97, -1215, -1267,
			-1066, -876, -760, 741, 841, 824, 235, 690, -1057, -334, -144, -134, -432, -126, 614, 699, -31, 798, 896, -248, -313, 132, -389, 37, 53, 154, -387, -250, -1784, 579, -1518, -1582, -1630,
			68, -23, -95, -1, 618, -448, 523, 25, -2, 54, 517, 187, 220, 688, 167, 137, 615, -88, -237, 365, 905, -1448, 156, 582, -1142, 872, -160, 723, -191, -1068, -1093, -1194, -1407, 63, -352,
			45, -884, -724, -444, -514, 219, -125, 21, 768, -359, -1397, -368, -746, -1049, -1730, 1711, 1573, -433, 627, -1258, -1270, -480, 814, -770, -655, -572, -503, -514, -1026, -355, 610, -95,
			-799, 108, -275, -253, -62, -241, -132, 76, -74, -288, -193, 631, -475, -1712, -1673, -214, 80, -962, -1069, -917, -1145, -1136, -1458, 611, 160, 622, -145, -575, -735, -350, 992, 868,
			896, 1003, 975, 1064, 1061, 1044, 1110, 1170, 1149, 1053, -1017, -1103, -326, -171, -1665, -21, -1680, -34, 863, -49, -108, -853, -1669, 1699, -1748, 1766, -586, 795, 771, 1496, 1395,
			1350, -1472, -1210, -671, -210, 563, 1301, 661, -345, -351, 91, 169, 257, -1345, 577, -515, -555, -261, -205, -205, -376, -444, -502, -606, -734, -829, -871, -928, -1043, -1297, -1319,
			-1427, -1508, -1587, -1775, -301, -467, -1602, -555, -325, -181, -1039, 57, -145, -160, -171, 133, -125, 174, -454, 552, -316, -67, 892, 630, -363, -311, 711, -151, -702, -12, -1005,
			-688, 1712, -1256, -584, -102, 1752, -290, 615, -1792, -1215, -393, -305, -223, -352, -341, -324, 1695, 1774, -1666, 740, 830, 858, 848, 862, 875, 866, 863, 873, 851, 877, 868, 866, 876,
			1013, 1014, 1018, 1050, 1162, 1087, 1121, 1182, 1499, 1344, 1353, 1397, 1654, 1767, 1316, 1579, 562, -668, -693, -125, 612, 669, 646, 649, -1067, -1684, 1762, 1717, -452, -452, -280,
			-283, -311 };

	public static PointF point() {
		PointF ret = new PointF(51.5f, 0.0f);
		String id = TimeZone.getDefault().getID();
		int i = 0;
		int len = LOCATION_ID.length;

		for (i = 0; i < len; i++) {
			if (LOCATION_ID[i].equals(id)) {
				ret.x = LOCATION_LONGITUDE_LIST[i] / 10.0f;
				ret.y = LOCATION_LATITUDE_LIST[i] / 10.0f;
				break;
			}
		}

		return ret;
	}

	public static boolean isjp() {
		return Locale.JAPAN.equals(Locale.getDefault());
	}
}
