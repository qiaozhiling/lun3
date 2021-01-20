package com.qzl.lun3;

import java.util.ArrayList;

public class Data {

    ArrayList<Integer> headPic;//头像
    ArrayList<String> groupName;//群名
    ArrayList<String> creator;//群聊创造者

    public Data() {
        headPic = new ArrayList<>();
        headPic.add(R.drawable.head1);
        headPic.add(R.drawable.head2);
        headPic.add(R.drawable.head3);
        headPic.add(R.drawable.head4);
        headPic.add(R.drawable.head5);
        headPic.add(R.drawable.head6);
        headPic.add(R.drawable.head7);

        groupName = new ArrayList<>();
        creator = new ArrayList<>();

        //随便来点初始数据
        int n = 1;
        for (int i = 0; i < n; i++) {
            groupName.add("" + (n - i));
            creator.add("" + (n - i));
        }

    }

}
