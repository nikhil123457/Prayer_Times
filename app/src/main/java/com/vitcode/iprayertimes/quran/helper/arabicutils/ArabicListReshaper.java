package com.vitcode.iprayertimes.quran.helper.arabicutils;

public class ArabicListReshaper {
    public static char[][] ARABIC_GLPHIES = {new char[]{1570, 65153, 65153, 65154, 65154, 2}, new char[]{1571, 65154, 65155, 65156, 65156, 2}, new char[]{1572, 65157, 65157, 65158, 65158, 2}, new char[]{1573, 65159, 65159, 65160, 65160, 2}, new char[]{1574, 65161, 65163, 65164, 65162, 4}, new char[]{1575, 1575, 1575, 65166, 65166, 2}, new char[]{1576, 65167, 65169, 65170, 65168, 4}, new char[]{1577, 65171, 65171, 65172, 65172, 2}, new char[]{1578, 65173, 65175, 65176, 65174, 4}, new char[]{1579, 65177, 65179, 65180, 65178, 4}, new char[]{1580, 65181, 65183, 65184, 65182, 4}, new char[]{1581, 65185, 65187, 65188, 65186, 4}, new char[]{1582, 65189, 65191, 65192, 65190, 4}, new char[]{1583, 65193, 65193, 65194, 65194, 2}, new char[]{1584, 65195, 65195, 65196, 65196, 2}, new char[]{1585, 65197, 65197, 65198, 65198, 2}, new char[]{1586, 65199, 65199, 65200, 65200, 2}, new char[]{1587, 65201, 65203, 65204, 65202, 4}, new char[]{1588, 65205, 65207, 65208, 65206, 4}, new char[]{1589, 65209, 65211, 65212, 65210, 4}, new char[]{1590, 65213, 65215, 65216, 65214, 4}, new char[]{1591, 65217, 65219, 65218, 65220, 4}, new char[]{1592, 65221, 65223, 65222, 65222, 4}, new char[]{1593, 65225, 65227, 65228, 65226, 4}, new char[]{1594, 65229, 65231, 65232, 65230, 4}, new char[]{1601, 65233, 65235, 65236, 65234, 4}, new char[]{1602, 65237, 65239, 65240, 65238, 4}, new char[]{1603, 65241, 65243, 65244, 65242, 4}, new char[]{1604, 65245, 65247, 65248, 65246, 4}, new char[]{1605, 65249, 65251, 65252, 65250, 4}, new char[]{1606, 65253, 65255, 65256, 65254, 4}, new char[]{1607, 65257, 65259, 65260, 65258, 4}, new char[]{1608, 65261, 65261, 65262, 65262, 2}, new char[]{1609, 65263, 65263, 65264, 65264, 2}, new char[]{1649, 1649, 1649, 64337, 64337, 2}, new char[]{1610, 65265, 65267, 65268, 65266, 4}, new char[]{1646, 64484, 64488, 64489, 64485, 4}, new char[]{1649, 1649, 1649, 64337, 64337, 2}, new char[]{1706, 64398, 64400, 64401, 64399, 4}, new char[]{1729, 64422, 64424, 64425, 64423, 4}, new char[]{1764, 1764, 1764, 1764, 65262, 2}};
    public static char DEFINED_CHARACTERS_ORGINAL_ALF = 1575;
    public static char DEFINED_CHARACTERS_ORGINAL_ALF_LOWER_HAMAZA = 1573;
    public static char DEFINED_CHARACTERS_ORGINAL_ALF_UPPER_HAMAZA = 1571;
    public static char DEFINED_CHARACTERS_ORGINAL_ALF_UPPER_MDD = 1570;
    public static char DEFINED_CHARACTERS_ORGINAL_LAM = 1604;
    public static char[] HARAKATE = {1536, 1537, 1538, 1539, 1542, 1543, 1544, 1545, 1546, 1547, 1549, 1550, 1552, 1553, 1554, 1555, 1556, 1557, 1558, 1559, 1560, 1561, 1562, 1563, 1566, 1567, 1569, 1595, 1596, 1597, 1598, 1599, 1600, 1611, 1612, 1613, 1614, 1615, 1616, 1617, 1618, 1619, 1620, 1621, 1622, 1623, 1624, 1625, 1626, 1627, 1628, 1629, 1630, 1632, 1642, 1643, 1644, 1647, 1648, 1650, 1748, 1749, 1750, 1751, 1752, 1753, 1754, 1755, 1756, 1759, 1760, 1761, 1762, 1763, 1764, 1765, 1766, 1767, 1768, 1769, 1770, 1771, 1772, 1773, 1774, 1775, 1750, 1751, 1752, 1753, 1754, 1755, 1756, 1757, 1758, 1759, 1776, 1789, 65136, 65137, 65138, 65139, 65140, 65141, 65142, 65143, 65144, 65145, 65146, 65147, 65148, 65149, 65150, 65151, 64606, 64607, 64608, 64609, 64610, 64611};
    public static char[][] LAM_ALEF_GLPHIES = {new char[]{15270, 65270, 65269}, new char[]{15271, 65272, 65271}, new char[]{1575, 65276, 65275}, new char[]{1573, 65274, 65273}};
    private String _returnString = "";

    public String getReshapedWord() {
        return this._returnString;
    }

    private char getReshapedGlphy(char c, int i) {
        int i2 = 0;
        while (true) {
            char[][] cArr = ARABIC_GLPHIES;
            if (i2 >= cArr.length) {
                return c;
            }
            if (cArr[i2][0] == c) {
                return cArr[i2][i];
            }
            i2++;
        }
    }

    private int getGlphyType(char c) {
        int i = 0;
        while (true) {
            char[][] cArr = ARABIC_GLPHIES;
            if (i >= cArr.length) {
                return 2;
            }
            if (cArr[i][0] == c) {
                return cArr[i][5];
            }
            i++;
        }
    }

    public boolean isHaraka(char c) {
        int i = 0;
        while (true) {
            char[] cArr = HARAKATE;
            if (i >= cArr.length) {
                return false;
            }
            if (cArr[i] == c) {
                return true;
            }
            i++;
        }
    }

    private String replaceLamAlef(String str) {
        char c;
        int length = str.length();
        char[] cArr = new char[length];
        str.getChars(0, length, cArr, 0);
        char c2 = 0;
        for (int i = 0; i < length - 1; i++) {
            if (!isHaraka(cArr[i]) && DEFINED_CHARACTERS_ORGINAL_LAM != cArr[i]) {
                c2 = cArr[i];
            }
            if (DEFINED_CHARACTERS_ORGINAL_LAM == cArr[i]) {
                char c3 = cArr[i];
                int i2 = i + 1;
                while (i2 < length && isHaraka(cArr[i2])) {
                    i2++;
                }
                if (i2 < length) {
                    if (i <= 0 || getGlphyType(c2) <= 2) {
                        c = getLamAlef(cArr[i2], c3, true);
                    } else {
                        c = getLamAlef(cArr[i2], c3, false);
                    }
                    if (c != 0) {
                        cArr[i] = c;
                        cArr[i2] = ' ';
                    }
                }
            }
        }
        return new String(cArr).replaceAll(" ", "").trim();
    }

    private char getLamAlef(char c, char c2, boolean z) {
        char c3 = z ? (char) 2 : 1;
        char c4 = 0;
        if (DEFINED_CHARACTERS_ORGINAL_LAM != c2) {
            return 0;
        }
        if (c == DEFINED_CHARACTERS_ORGINAL_ALF_UPPER_MDD) {
            c4 = LAM_ALEF_GLPHIES[0][c3];
        }
        if (c == DEFINED_CHARACTERS_ORGINAL_ALF_UPPER_HAMAZA) {
            c4 = LAM_ALEF_GLPHIES[1][c3];
        }
        if (c == DEFINED_CHARACTERS_ORGINAL_ALF_LOWER_HAMAZA) {
            c4 = LAM_ALEF_GLPHIES[3][c3];
        }
        return c == DEFINED_CHARACTERS_ORGINAL_ALF ? LAM_ALEF_GLPHIES[2][c3] : c4;
    }

    public ArabicListReshaper(String str) {
        DecomposedWord decomposedWord = new DecomposedWord(replaceLamAlef(str));
        if (decomposedWord.stripedRegularLetters.length > 0) {
            this._returnString = reshapeIt(new String(decomposedWord.stripedRegularLetters));
        }
        this._returnString = decomposedWord.reconstructWord(this._returnString);
    }

    class DecomposedWord {
        int[] harakatesPositions;
        int[] lettersPositions;
        char[] stripedHarakates;
        char[] stripedRegularLetters;

        DecomposedWord(String str) {
            int length = str.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (ArabicListReshaper.this.isHaraka(str.charAt(i2))) {
                    i++;
                }
            }
            this.harakatesPositions = new int[i];
            this.stripedHarakates = new char[i];
            int i3 = length - i;
            this.lettersPositions = new int[i3];
            this.stripedRegularLetters = new char[i3];
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < str.length(); i6++) {
                if (ArabicListReshaper.this.isHaraka(str.charAt(i6))) {
                    this.harakatesPositions[i5] = i6;
                    this.stripedHarakates[i5] = str.charAt(i6);
                    i5++;
                } else {
                    this.lettersPositions[i4] = i6;
                    this.stripedRegularLetters[i4] = str.charAt(i6);
                    i4++;
                }
            }
        }

        public String reconstructWord(String str) {
            char[] cArr = new char[(str.length() + this.stripedHarakates.length)];
            int i = 0;
            int i2 = 0;
            while (true) {
                int[] iArr = this.lettersPositions;
                if (i2 >= iArr.length) {
                    break;
                }
                cArr[iArr[i2]] = str.charAt(i2);
                i2++;
            }
            while (true) {
                int[] iArr2 = this.harakatesPositions;
                if (i >= iArr2.length) {
                    return new String(cArr);
                }
                cArr[iArr2[i]] = this.stripedHarakates[i];
                i++;
            }
        }
    }

    public String reshapeIt(String str) {
        int i;
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        char[] cArr = new char[length];
        str.getChars(0, length, cArr, 0);
        stringBuffer.append(getReshapedGlphy(cArr[0], 2));
        int i2 = 1;
        while (true) {
            i = length - 1;
            if (i2 >= i) {
                break;
            }
            if (getGlphyType(cArr[i2 - 1]) == 2) {
                stringBuffer.append(getReshapedGlphy(cArr[i2], 2));
            } else {
                stringBuffer.append(getReshapedGlphy(cArr[i2], 3));
            }
            i2++;
        }
        if (length >= 2) {
            if (getGlphyType(cArr[length - 2]) == 2) {
                stringBuffer.append(getReshapedGlphy(cArr[i], 1));
            } else {
                stringBuffer.append(getReshapedGlphy(cArr[i], 4));
            }
        }
        return stringBuffer.toString();
    }
}
