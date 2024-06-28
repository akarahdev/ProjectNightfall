package me.endistic.skyblock.utils.text;

import org.bukkit.ChatColor;

public class Word implements TextElement {
    public String getWord() {
        return word;
    }

    public Word setWord(String word) {
        this.word = word;
        return this;
    }

    public String word;

    public Word(String word) {
        this.word = word;
    }

    @Override
    public String render() {
        var tw = this.word;
        if(tw.matches("-?\\d"))
            tw = ChatColor.RED + tw;
        return tw;
    }

}
