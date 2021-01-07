package com.qzl.lun3;

import java.util.ArrayList;

public class Data {

    ArrayList<Integer> headPic;//头像
    ArrayList<String> groupName;//群名
    ArrayList<String> creator;//群聊创造者

    public Data() {

        headPic = new ArrayList<>();
        headPic.add(R.drawable.rua);
        headPic.add(R.drawable.head1);
        headPic.add(R.drawable.head2);
        headPic.add(R.drawable.head3);
        headPic.add(R.drawable.head4);

        groupName = new ArrayList<>();
        creator = new ArrayList<>();

        groupName.add("A");
        creator.add("a");
    }

}
