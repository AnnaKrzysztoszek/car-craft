package pl.com.bottega.carcraft.model.sandbox;

import com.sun.tools.javac.code.Type;

/**
 * Created by anna on 19.11.2016.
 */
public class Box<ContentType extends Weighable, PackageType> implements Weighable {//ContentType ma dziedziczyć po Weighable, która nie musi być interfejsem

    private final int maxWeight;
    private ContentType content;
    private PackageType packing;

    public Box(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public  void put(ContentType content) {
        if (maxWeight < content.getWeight())
            throw new IllegalArgumentException("Content too heavy");
        this.content = content;
    }

    public ContentType pop() {
        return content;
    }

    public void wrap(PackageType packing){
        this.packing = packing;
    }

    public <NewContentType extends Weighable> Box<NewContentType, PackageType> change(Changer<ContentType, NewContentType> changer) {
        Box<NewContentType, PackageType> newBox = new Box<>(maxWeight);
        NewContentType newContent = changer.change(content);
        newBox.put(newContent);
        return newBox;
    }

    @Override
    public int getWeight() {
        if (content == null)
            return 100;
        return content.getWeight() + 100;
    }
}
