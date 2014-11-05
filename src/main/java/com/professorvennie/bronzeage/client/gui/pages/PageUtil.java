package com.professorvennie.bronzeage.client.gui.pages;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.StatCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProfessorVennie on 10/25/2014 at 4:12 PM.
 */
public class PageUtil {

    @SideOnly(Side.CLIENT)
    public static void renderText(int x, int y, int width, int height, String unlocalizedText) {
        FontRenderer renderer = Minecraft.getMinecraft().fontRenderer;
        boolean unicode = renderer.getUnicodeFlag();
        renderer.setUnicodeFlag(true);
        String text = StatCollector.translateToLocal(unlocalizedText).replaceAll("&", "\u00a7");
        String[] textEntries = text.split("<br>");

        String lastFormat = "";
        String pendingFormat = "";
        for (String s : textEntries) {
            List<String> wrappedLines = new ArrayList();
            String workingOn = "";

            int i = 0;
            String[] tokens = s.split(" ");
            for (String s1 : tokens) {
                boolean skipPending = false;
                String format = getFormatFromString(s1);

                if (!format.isEmpty() && s1.length() > 0 && s1.charAt(0) != '\u00a7') {
                    skipPending = true;
                    pendingFormat = format;
                    format = "";
                }

                if (!pendingFormat.isEmpty() && !skipPending) {
                    format = pendingFormat;
                    pendingFormat = "";
                }

                /*if (MathHelper.stringNullOrLengthZero(format))
                    format = lastFormat;*/

                if (renderer.getStringWidth(workingOn + " " + s1) >= width) {
                    wrappedLines.add(workingOn);
                    workingOn = "";
                }
                workingOn = workingOn + format + " " + s1;

                if (i == tokens.length - 1)
                    wrappedLines.add(workingOn);

                ++i;
                lastFormat = format;
            }

            for (String s1 : wrappedLines) {
                y += 10;
                renderer.drawString(s1, x, y, 0);
            }

            y += 10;
        }

        renderer.setUnicodeFlag(unicode);
    }

    public static String getFormatFromString(String par0Str) {
        String s1 = "";
        int i = -1;
        int j = par0Str.length();

        while ((i = par0Str.indexOf(167, i + 1)) != -1) {
            if (i < j - 1) {
                char c0 = par0Str.charAt(i + 1);

                if (isFormatColor(c0))
                    s1 = "\u00a7" + c0;
                else if (isFormatSpecial(c0))
                    s1 = s1 + "\u00a7" + c0;
            }
        }

        return s1;
    }

    public static boolean isFormatColor(char par0) {
        return par0 >= 48 && par0 <= 57 || par0 >= 97 && par0 <= 102 || par0 >= 65 && par0 <= 70;
    }

    public static boolean isFormatSpecial(char par0) {
        return par0 >= 107 && par0 <= 111 || par0 >= 75 && par0 <= 79 || par0 == 114 || par0 == 82;
    }
}
