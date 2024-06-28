package me.endistic.skyblock.utils.text;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Text implements TextElement {
    public ArrayList<TextElement> elements = new ArrayList<>();

    public Text(String input) {
        var split = input.split(" ");
        for(var s : split) {
            if(s.startsWith("${"))
                this.elements.add(new Placeholder(s));
            else
                this.elements.add(new Word(s));
        }
    }

    @Override
    public String render() {
        return elements
            .stream()
            .map(TextElement::render)
            .collect(Collectors.joining(" "));
    }

    public List<String> renderItemLore() {
        var chars = 0;
        var builder = new StringBuilder();
        var list = new ArrayList<String>();
        var split = this.elements;
        for(var s : split) {
            builder.append(ChatColor.GRAY);
            builder.append(s.render());
            builder.append(" ");

            chars += s.render().length();
            if(chars > 30) {
                chars = 0;
                list.add(builder.toString());
                builder = new StringBuilder();
            }
        }
        list.add(builder.toString());
        return list;
    }
}
