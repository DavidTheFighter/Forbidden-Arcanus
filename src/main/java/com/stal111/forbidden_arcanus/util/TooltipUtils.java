package com.stal111.forbidden_arcanus.util;

import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class TooltipUtils {

    public static int shiftTextByLines(List<? extends ITextProperties> lines, int y) {
        for(int i = 1; i < lines.size(); i++) {
            String s = lines.get(i).getString();
            s = TextFormatting.getTextWithoutFormattingCodes(s);
            if(s != null && s.trim().isEmpty()) {
                y += 10 * (i - 1) + 1;
                break;
            }
        }
        return y;
    }
}
