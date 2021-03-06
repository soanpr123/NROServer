package server;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import jdk.nashorn.internal.ir.BreakNode;
import real.item.Item;
import real.item.ItemOption;
import real.item.ItemSell;
import real.item.ItemTemplate;
import real.item.ItemTemplates;
import real.item.Shop;
import real.item.TabItemShop;
import real.map.Map;
import real.map.Npc;
import real.map.Zone;
import real.player.Player;
import real.player.PlayerManger;
import server.io.Message;
import server.io.Session;

public class Menu {
    Server server = Server.gI();
    public static void doMenuArray(Player p,int idnpc,String chat, String[] menu) {
        Message m = null;
        try {
            m = new Message(32);
            m.writer().writeShort(idnpc);
            m.writer().writeUTF(chat);
            m.writer().writeByte(menu.length);
            for (byte i = 0; i < menu.length; ++i) {
            m.writer().writeUTF(menu[i]);
            }
            m.writer().flush();
            p.session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(m != null) {
                m.cleanup();
            }
        }

    }
    public static void doMenuArraySay(Player p,short id, String[] menu) {
        Message m = null;
        try {
            m = new Message(38);
            m.writer().writeShort(id);
            for(byte i = 0; i < menu.length; i++) {
                m.writer().writeUTF(menu[i]);
            }
            m.writer().flush();
            p.session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(m != null) {
                m.cleanup();
            }
        }

    }
    public static void sendWrite(Player p, String title,short type) {
        Message m = null;
        try {
            m = new Message(88);
            m.writer().writeUTF(title);
            m.writer().writeShort(type);
            m.writer().flush();
            p.session.sendMessage(m);
            m.cleanup();
        } catch (IOException var5) {
            var5.printStackTrace();
        } finally {
            if(m != null) {
                m.cleanup();
            }
        }

    }
    public void textBoxId(Session session,short menuId, String str) {
        Message msg;
        try {
            msg = new Message(88);
            msg.writer().writeInt(menuId);
            msg.writer().writeUTF(str);
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }
     public void sendTB(Session session, Player title, String s) {
        Message m = null;
        try {
            m = new Message(94);
            m.writer().writeUTF(title.name);
            m.writer().writeUTF(s);
            m.writer().flush();
            PlayerManger.gI().SendMessageServer(m);
            session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(m != null) {
                m.cleanup();
            }
        }

    }
     public void ChatTG(Player p, int avatar, String chat3,byte cmd) {
        Message m = null;
        try {
            m = new Message(-70);
            m.writer().writeShort(avatar);
            m.writer().writeUTF(chat3);
            m.writer().writeByte(cmd);
            m.writer().flush();
            PlayerManger.gI().SendMessageServer(m);
            p.session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(m != null) {
                m.cleanup();
            }
        }
    }
     public void ChatTG(Player p,short avatar, String str) {
        Message m = null;
        try {
            m = new Message(94);
            m.writer().writeShort(avatar);
            m.writer().writeUTF(str);
            m.writer().flush();
            p.session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(m != null) {
                m.cleanup();
            }
        }
    }
     

     public void Test(Player p) {
        Message m = null;
        try {
//            Item[] shop = p.ItemBag;
//            
//            System.out.println("bag "+ p.ItemBag);
            m = new Message(-81);
            m.writer().writeByte(0);
            m.writer().writeUTF("Ng???c R???ng Z");
            m.writer().writeUTF("Ch???n ????? n??ng c???p");
            m.writer().flush();
            p.session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(m != null) {
                m.cleanup();
            }
        }
    }
     public void LuckyRound(Player p,byte type,byte soluong) throws IOException{
         Message m = null;
        try {
         if(type == 0){
         m = new Message(-127);
         m.writer().writeByte(type);
         short[] arId = new short[]{2280,2281,2282,2283,2284,2285,2286};
         m.writer().writeByte(7);
         for(short i = 0; i< arId.length ;i++){
         m.writer().writeShort(arId[i]); 
         }
         m.writer().writeByte(soluong);
         m.writer().writeInt(10000);
         m.writer().writeShort(0);
         m.writer().flush();
         p.session.sendMessage(m);
         }else if(type == 1){
         m = new Message(-127);
         m.writer().writeByte(soluong);
         short[] arId = new short[]{2,3,4,5,6,7,8};
         for(short i = 0; i< soluong ;i++){
         m.writer().writeShort(arId[i]); 
         }
         m.writer().flush();
         p.session.sendMessage(m);
         }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(m != null) {
                m.cleanup();
            }
        }
     }
     public void confirmMenu(Player p, Message m) throws IOException, SQLException, InterruptedException {
        Short idNpc = m.reader().readShort();
        byte select = m.reader().readByte();
        switch (p.menuNPCID) {
        case 39:{
            if(select == 0){
                TabItemShop[] test = Shop.getTabShop(39, 2).toArray(new TabItemShop[0]);
                GameScr.UIshop(p, test);
                break;
            }
            break;
        }
        case 16:{
            if(select == 0){
                TabItemShop[] test = Shop.getTabShop(16, p.gender).toArray(new TabItemShop[0]);
                GameScr.UIshop(p, test);
                break;
            }
            break;
        }
        case 9:{
            if(select == 0){
                TabItemShop[] test = Shop.getTabShop(9, 0).toArray(new TabItemShop[0]);
                GameScr.UIshop(p, test);
                break;
            }
            break;
        }
        case 7:
            if(select == 0){
            TabItemShop[] test = Shop.getTabShop(7, 1).toArray(new TabItemShop[0]);
            GameScr.UIshop(p, test);
            break;
            }
            break;
        case 8:
            if(select == 0){
            TabItemShop[] test = Shop.getTabShop(8, 2).toArray(new TabItemShop[0]);
            GameScr.UIshop(p, test);
            break;
            }
            break;
        case 38:
            if(select == 0){
                if(p.map.id!=102){
                    GotoMap(p,102);
                }else{
                    GotoMap(p,24+p.gender);
                }
            }
            if(select == 1){
                break;
            }
            break;
        case 25:{
            if(select == 0){
                if(p.clan == null){
                    p.sendAddchatYellow("Ng????i ch??a c?? bang h???i!");
                }
                else{
                    if(p.clan.members.size() < 5){
                        p.sendAddchatYellow("Bang h???i c???a ng????i qu?? y???u h??y k???t n???p ????? 5 th??nh vi??n ????? v??o!");
                    }
                    else{
                        for(int i = 0 ; i < p.map.players.size();i++){
                            if(p.map.players.get(i).clan.id == p.clan.id){
                                GotoMap(p,53);
                            }else{
                                p.sendAddchatYellow("Ph???i c?? ??t nh???t 1 th??nh vi??n c?? m???t trong khu!");
                            }
                        }
                    }
                }
            }
            if(select == 1){
                break;
            }
            break;
        }
        case 13 :{
            if(p.menuID != -1)
            {
                if(p.menuID == 1 && select == 0)
                {
                    p.openBox();
                }
                if(p.menuID == 1 && select == 1)
                {
                    ExtendBag(p);
                }
                if(p.menuID == 1 && select == 2)
                {
                    ExtendBox(p);
                }
            }
            if(select == 1){
                doMenuArray(p,idNpc,"Ta c?? th??? gi??p g?? cho con",new String[]{"M??? r????ng" , "M??? r???ng\nH??nh trang" ,"M??? r???ng\nR????ng ?????"});
                p.menuID = select;
            }
            break;
        }
        case 21 :{
            if(p.menuID != -1)
            {
                if(p.menuID == 1 && select == 0)
                {
                    TabItemShop[] test = Shop.getTabShop(21, 0).toArray(new TabItemShop[0]);
                    GameScr.UIshop(p, test);
                }
            }
            if(select == 1){
                doMenuArray(p,idNpc,"B??a c???a ta r???t l???i h???i. Mua xong c?? t??c d???ng ngay nh??, nh??? tranh th??? s??? d???ng, tho??t game ph?? l???m. Mua c??ng nhi???u th???i gian gi?? c??ng r???!",new String[]{"B??a\n1 th??ng"});
                p.menuID = select;
            }
            break;
        }
        case 1:
                if(select == 0){
                    p.vang += 500000000;
                    Service.gI().buyDone(p);
                    p.sendAddchatYellow("Nh???n th??nh c??ng 500 tri???u v??ng");
                    break;
                }
                if(select == 1){
                    if(p.ngoc < 10000){
                         p.ngoc = p.ngoc + 10000;
                        Service.gI().buyDone(p);
                        p.sendAddchatYellow("Nh???n th??nh c??ng 10k ng???c");
                    }
                    else{
                        p.sendAddchatYellow("Vui l??ng s??? d???ng h???t ng???c");
                    }
                    break;
                }
            case 0:
                if(select == 0){
                    p.vang += 500000000;
                    Service.gI().buyDone(p);
                    p.sendAddchatYellow("Nh???n th??nh c??ng 500 tri???u v??ng");
                    break;
                }
                if(select == 1){
                    if(p.ngoc < 10000){
                         p.ngoc = p.ngoc + 10000;
                        Service.gI().buyDone(p);
                        p.sendAddchatYellow("Nh???n th??nh c??ng 10k ng???c");
                    }
                    else{
                        p.sendAddchatYellow("Vui l??ng s??? d???ng h???t ng???c");
                    }
                    break;
                }
            case 2:
                if(select == 0){
                    p.vang += 500000000;
                    Service.gI().buyDone(p);
                    p.sendAddchatYellow( "Nh???n th??nh c??ng 500 tri???u v??ng");
                    break;
                }
                if(select == 1){
                    if(p.ngoc < 10000){
                        p.ngoc = p.ngoc + 10000;
                        Service.gI().buyDone(p);
                       p.sendAddchatYellow( "Nh???n th??nh c??ng 10k ng???c");
                    }
                    else{
                        p.sendAddchatYellow("Vui l??ng s??? d???ng h???t ng???c");
                    }
                    break;
                }
            case 12:
                if(p.map.id == 19){
                    if(select == 0){
                        GotoMap(p,109);
                    }
                    if(select == 1){
                        GotoMap(p,68);
                    }
                    if(select == 2){
                        break;
                    }
                }else if(p.map.id == 68){
                    if(select == 0){
                        GotoMap(p,19);
                    }
                    if(select == 1){
                        break;
                    }
                }
                else{
                    if(select == 0){
                        GotoMap(p,24);
                    }
                    if(select == 1){
                        GotoMap(p,25);
                    }
                    if(select == 2){
                        break;
                    }
                }
                break;
            case 11:
                if(select == 0){
                    GotoMap(p,24);
                }
                if(select == 1){
                    GotoMap(p,26);
                }
                if(select == 2){
                    break;
                }
                break;
            case 10:
                if(select == 0){
                    GotoMap(p,25);
                }
                if(select == 1){
                    GotoMap(p,26);
                }
                if(select == 2){
                    break;
                }
                break;
        default:{
            Service.gI().sendTB(p.session,0, "Ch???c N??ng ??ang ???????c C???p Nh???t " + idNpc,0);
            break;
        }
        }
        m.cleanup();
     }
     public void ExtendBag(Player p){
         String CREATE_PLAYER_BAG = "UPDATE player SET maxluggage=? WHERE account_id=?";
         Connection conn = DBService.gI().getConnection();
         try {
            if(p.maxluggage >= 40){
                p.sendAddchatYellow( "???? ?????t gi???i h???n");
                return;
            }
            else{
                p.maxluggage += 10;
                PreparedStatement ps = conn.prepareStatement(CREATE_PLAYER_BAG);
                ps.setInt(1, p.maxluggage);
                ps.setInt(2, p.id);
                ps.executeUpdate();
                p.sendAddchatYellow("M??? r???ng th??nh c??ng 10 ?? h??nh trang vui l??ng ????ng nh???p l???i game ????? c?? hi???u l???c");
            }
            conn.close();
         }
         catch (Exception e) {
            e.printStackTrace();
        }
     }
     public void ExtendBox(Player p){
         String CREATE_PLAYER_BOX = "UPDATE player SET maxbox=? WHERE account_id=?";
         Connection conn = DBService.gI().getConnection();
         try {
            if(p.maxBox >= 50){
                p.sendAddchatYellow( "???? ?????t gi???i h???n");
                return;
            }
            else{
                p.maxBox += 10;
                PreparedStatement ps = conn.prepareStatement(CREATE_PLAYER_BOX);
                ps.setInt(1, p.maxBox);
                ps.setInt(2, p.id);
                ps.executeUpdate();
                
                p.sendAddchatYellow( "M??? r???ng th??nh c??ng 10 ?? r????ng ????? vui l??ng ????ng nh???p l???i game ????? c?? hi???u l???c");
            }
            conn.close();
         }
         catch (Exception e) {
            e.printStackTrace();
        }
     }
     public void GotoMap(Player p,int id){
        Map maptele = Manager.getMapid(id);
        Controller.getInstance().teleportToMAP(p, maptele);
     }
     public void menuHandler(Player p, Message m) throws IOException, SQLException, InterruptedException {
        byte idNPC = m.reader().readByte();// ID NPC
        byte menuID = m.reader().readByte();// L???p n??t 1
        byte select = m.reader().readByte();// L???p n??t 2
         System.out.println("menuID: "+ p.menuID);
         System.out.println("menuNPCID: "+ p.menuNPCID);
         System.out.println("select: "+ select);
        int tl;
        switch (p.menuNPCID) {
        
        case 13 :
            if(p.menuID == 1)
            {
                if(select == 0){
                p.openBox();
                }
            }
            break;
        
        default:{
            Service.gI().sendTB(p.session,0, "Ch???c N??ng ??ang ???????c C???p Nh???t " + idNPC,0);
                break;
        }
       
         //   Service.getInstance().serverMessage(p.session,"ID NPC " + b1);
         }
        m.cleanup();
     }
     
     public  void openUINpc(Player p, Message m) throws IOException {
        short idnpc = m.reader().readShort();//idnpc
        int avatar;
        m.cleanup();
        p.menuID = -1;
        p.menuNPCID = idnpc;
        avatar = NpcAvatar(p, idnpc);
        m = new Message(33);
        if(p.menuNPCID == 21){
                doMenuArray(p,idnpc,"Ng????i t??m ta c?? vi???c g???",new String[]{"N??i chuy???n","C???a h??ng\nB??a","N??ng c???p\nV???t ph???m","Nh???p\nNg???c R???ng"});
                return;
            }
            if(p.menuNPCID == 10){
                doMenuArray(p,idnpc,"T??u V?? Tr??? c???a t??i c?? th??? ????a c???u ?????n h??nh tinh kh??c trong 3 gi??y. C???u mu???n ??i ????u",new String[]{"?????n\nNam???c","?????n\nXayda","T??? Ch???i"});
                return;
            }
            if(p.menuNPCID == 11){
                doMenuArray(p,idnpc,"T??u V?? Tr??? Nam???c tuy c?? nh??ng t???c ????? kh??ng h??? k??m b???t k??? lo???i t???u n??o kh??c. C???u mu???n ??i ????u?",new String[]{"?????n\nTr??i ?????t","?????n\nXayda","T??? Ch???i"});
                return;
            }
            if(p.menuNPCID == 12){
                if(p.map.id == 19){
                    doMenuArray(p,idnpc,"?????i qu??n Fide ??ang ??? Thung L??ng Nappa, ta s??? ????a ng????i ?????n ????",new String[]{"?????n\nCold","?????n\nNappa","T??? Ch???i"});
                }else if(p.map.id == 68){
                    doMenuArray(p,idnpc,"Ng????i mu???n b??? ch???y ???",new String[]{"?????ng ??","T??? Ch???i"});
                }else{
                    doMenuArray(p,idnpc,"T??u v?? tr??? Xayda s??? d???ng c??ng ngh??? m???i nh???t, c?? th??? ????a ng????i ??i b???t k??? ????u, ch??? c???n tr??? ti???n l?? ???????c",new String[]{"?????n\nTr??i ?????t","?????n\nNam???c","T??? Ch???i"});
                }
                return;
            }
            if(p.menuNPCID == 38){
                if(p.map.id != 102){
                doMenuArray(p,idnpc,"Ch??o ch?? ch??u c?? th??? gi??p g???",new String[]{"??i ?????n T????ng lai","T??? Ch???i"});
                return;
                }else{
                    doMenuArray(p,idnpc,"Ch??o ch?? ch??u c?? th??? gi??p g???",new String[]{"Quay v???\nQu?? kh???","T??? Ch???i"});
                    return;
                }
            }
            if (p.menuNPCID == 16) {
                doMenuArray(p,idnpc,Text.get(0, 1),new String[]{"C???a H??ng"});
                return;
            }
            if (p.menuNPCID == 25) {
            doMenuArray(p,idnpc,"Ng????i c?? ch???c ch???n mu???n v??o tr???i ?????c nh??n",new String[]{"V??o Doanh Tr???i","T??? Ch???i"});
            return;
            }
            if (p.menuNPCID == 1 || p.menuNPCID == 0 ||p.menuNPCID == 2) {
//                if(p.HavePet == 0){
//                    doMenuArray(p,idnpc,Text.get(0, 0),new String[]{"Nh???n V??ng","Nh???n Ng???c","Nh???n ????? T???"});
//                }else{
//                    doMenuArray(p,idnpc,Text.get(0, 0),new String[]{"Nh???n V??ng","Nh???n Ng???c"});
//                }
                doMenuArray(p,idnpc,Text.get(0, 0),new String[]{"Nh???n V??ng","Nh???n Ng???c","Nh???n ????? T???"});
                return;
            }
            if (p.menuNPCID == 39) 
            {
                doMenuArray(p,idnpc,Text.get(0, 1),new String[]{"C???a H??ng"});
                return;
            }
            if (p.menuNPCID == 9) 
            {
                if(p.gender == 2){
                    doMenuArray(p,idnpc,Text.get(0, 1),new String[]{"C???a H??ng"});
                }else{
                    Service.gI().sendTB(p.session,0, "Ta ch??? b??n ????? cho h??nh tinh Xayda",0);
                }
                return;
            }
            if (p.menuNPCID == 7) {
                if(p.gender == 0){
                    doMenuArray(p,idnpc,Text.get(0, 1),new String[]{"C???a H??ng"});
                }else{
                    Service.gI().sendTB(p.session,0, "Ta ch??? b??n ????? cho h??nh tinh Tr??i ?????t",0);
                }
                return;
            }
            if (p.menuNPCID == 8) {
                if(p.gender == 1){
                    doMenuArray(p,idnpc,Text.get(0, 1),new String[]{"C???a H??ng"});

                }else{
                    Service.gI().sendTB(p.session,0, "Ta ch??? b??n ????? cho h??nh tinh Nam???c",0);
                }
                return;
            }
            if (p.menuNPCID == 13) {
            doMenuArray(p,idnpc,"Ta c?? th??? gi??p g?? cho con",new String[]{"N??i chuy???n","T??nh n??ng"});
            return;
            }
            if(p.menuNPCID == 3){
            p.openBox();
            return;
            }else{
                Service.gI().sendTB(p.session,0, "Ch???c N??ng ??ang ???????c C???p Nh???t " + idnpc,0);
            }
            m.writer().flush();
            p.session.sendMessage(m);
            m.cleanup();
    }
    public int NpcAvatar(Player p,int npcID){
       
        for (int i = 0; i < p.getPlace().map.template.npcs.length; i++){
            if(p.getPlace().map.template.npcs[i].tempId == npcID)
            {
                return p.getPlace().map.template.npcs[i].avartar;
            }    

        }
        return -1;
    }
}
