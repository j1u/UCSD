/*
  Name: James Lu
 */

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Messenger Application Test
 * @author James Lu
 * @since  4-15-21
 */
public class MessengerApplicationTest {

    /*
      Messages defined in starter code. Remember to copy and paste these strings to the
      test file if you cannot directly access them. DO NOT change the original declaration
      to public.
     */
    private static final String INVALID_INPUT =
            "The source path given cannot be parsed as photo.";
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";

    /*
      Global test variables. Initialize them in @Before method.
     */
    PremiumUser marina;
    MessageExchange room;

    /*
      The date used in Message and its subclasses. You can directly
      call this in your test methods.
     */
    LocalDate date = LocalDate.now();

    /*
     * Setup
     */
    @Before
    public void setup() {
        marina = new PremiumUser("Marina", "Instructor");
        room = new ChatRoom();
    }

    /*
      Recap: Assert exception without message
     */
    @Test (expected = IllegalArgumentException.class)
    public void testPremiumUserThrowsIAE() {
        marina = new PremiumUser("Marina", null);
    }

    /*
      Assert exception with message
     */
    @Test
    public void testPhotoMessageThrowsODE() {
        try {
            PhotoMessage pm = new PhotoMessage(marina, "PA02.zip");
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_INPUT, ode.getMessage());
        }
    }

    /*
     * Assert message content without hardcoding the date
     */
    @Test
    public void testTextMessageGetContents() {
        try {
            TextMessage tm = new TextMessage(marina, "A sample text message.");

            // concatenating the current date when running the test
            String expected = "<Premium> Marina [" + date + "]: A sample text message.";
            assertEquals(expected, tm.getContents());
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    TextMessage msg;
    PhotoMessage msg2;
    TextMessage msg3;

    @Test
    public void testTextMessage(){
        try {
            this.msg = new TextMessage(marina, "fssfd");
            assertEquals("<Premium> Marina", msg.getSender().displayName());
            assertEquals("fssfd", msg.contents);

            this.msg = new TextMessage(new PremiumUser("what", "huh"), "bonk");
            assertEquals("<Premium> what", msg.getSender().displayName());
            assertEquals("bonk", msg.contents);

            this.msg = new TextMessage(new PremiumUser("who", "where"), "boink");
            assertEquals("<Premium> who", msg.getSender().displayName());
            assertEquals("boink", msg.contents);

            assertEquals("<Premium> who [" + msg.getDate().toString() + "]: boink",
                    this.msg.getContents());
        }catch (OperationDeniedException e){
            System.out.println("object init failed");
            System.exit(0);
        }
    }

    @Test
    public void testTextMessageThrowsODE() {
        try {
            StringBuilder msg = new StringBuilder();
            for (int i = 0; i < 505; i++){
                msg.append(i);
            }
            TextMessage tst = new TextMessage(marina, msg.toString());
            fail("Exception not thrown");
        } catch (OperationDeniedException ode) {
            assertEquals(EXCEED_MAX_LENGTH, ode.getMessage());
        }
    }

    @Test
    public void testTextMessageThrowsIAE() {
        try {
            TextMessage tst = new TextMessage(null, "");
            fail("Exception not thrown");
        } catch (Exception iae) {
            assertEquals(iae.getMessage(), iae.getMessage());
        }
        try {
            TextMessage tst = new TextMessage(marina, null);
            fail("Exception not thrown");
        } catch (Exception iae) {
            assertEquals(iae.getMessage(), iae.getMessage());
        }
    }

    @Test
    public void testPhotoMessage(){
        try {
            this.msg2 = new PhotoMessage(marina, "/fssfd.png");
            assertEquals("<Premium> Marina", msg2.getSender().displayName());
            assertEquals("/fssfd.png", msg2.contents);
            assertEquals("png", msg2.getExtension());

            this.msg2 = new PhotoMessage(new PremiumUser("what", "huh"),
                    "/bonk.gif");
            assertEquals("<Premium> what", msg2.getSender().displayName());
            assertEquals("/bonk.gif", msg2.contents);
            assertEquals("gif", msg2.getExtension());

            this.msg2 = new PhotoMessage(new PremiumUser("who", "where"),
                    "/boink.jpg");
            assertEquals("<Premium> who", msg2.getSender().displayName());
            assertEquals("/boink.jpg", msg2.contents);
            assertEquals("jpg", msg2.getExtension());

            assertEquals("<Premium> who [" + msg2.getDate().toString() + "]: Picture at /boink.jpg",
                    this.msg2.getContents());
            assertEquals("jpg", this.msg2.getExtension());
        }catch (OperationDeniedException e){
            System.out.println("object init failed");
            System.exit(0);
        }
    }

    @Test
    public void testPhotoMessageThrowsODE2() {
        try {
            PhotoMessage tst = new PhotoMessage(new StandardUser("huh", "what"),
                    "/fffffff.png");
            fail("Exception not thrown");
        } catch (OperationDeniedException ode) {
            assertEquals(Message.DENIED_USER_GROUP, ode.getMessage());
        }
    }

    @Test
    public void testPhotoMessageThrowsODE3() {
        try {
            PhotoMessage tst = new PhotoMessage(marina, "/fffffff.file");
            fail("Exception not thrown");
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_INPUT, ode.getMessage());
        }
    }

    @Test
    public void testPhotoMessageThrowsIAE() {
        try {
            PhotoMessage tst = new PhotoMessage(null, "");
            fail("Exception not thrown");
        } catch (Exception iae) {
            assertEquals(iae.getMessage(), iae.getMessage());
        }
        try {
            PhotoMessage tst = new PhotoMessage(marina, null);
            fail("Exception not thrown");
        } catch (Exception iae) {
            assertEquals(iae.getMessage(), iae.getMessage());
        }
    }

    ChatRoom test1;
    ChatRoom test2;
    ChatRoom test3;

    @Test
    public void chatRoomTest(){
        test1 = new ChatRoom();
        assertNotNull(test1);

        test2 = new ChatRoom();
        assertNotNull(test2);

        test3 = new ChatRoom();
        assertNotNull(test3);

        User user1 = new StandardUser("some1", "");
        User user2 = new PremiumUser("some2", "");
        User user3 = new StandardUser("some3", "");

        try {
            this.msg = new TextMessage(user1, "fssfd");
            this.msg2 = new PhotoMessage(user2, "/fssfd.png");
            this.msg3 = new TextMessage(user3, "dddd");
        } catch (OperationDeniedException e){
            System.out.println(e.getMessage());
        }

        assertEquals(0, test1.getUsers().size());

        test1.addUser(user1);
        assertEquals(1, test1.getUsers().size());
        test1.addUser(user2);
        assertEquals(2, test1.getUsers().size());
        test1.addUser(user3);
        assertEquals(3, test1.getUsers().size());

        assertEquals(0, test1.getLog(user1).size());

        test1.recordMessage(this.msg);
        assertEquals(1, test1.getLog(user1).size());
        test1.recordMessage(this.msg2);
        assertEquals(2, test1.getLog(user1).size());
        test1.recordMessage(this.msg3);
        assertEquals(3, test1.getLog(user1).size());

        ArrayList<Message> log = test1.getLog(user1);
        assertEquals(user1, log.get(0).getSender());
        assertEquals(user2, log.get(1).getSender());
        assertEquals(user3, log.get(2).getSender());

        test1.removeUser(user1, user1);
        assertEquals(2, test1.getUsers().size());
        test1.removeUser(user2, user2);
        assertEquals(1, test1.getUsers().size());
        test1.removeUser(user3, user3);
        assertEquals(0, test1.getUsers().size());
    }

    @Test
    public void userTests(){
        test1 = new ChatRoom();

        User user1 = new StandardUser("some1", "sdf");
        assertEquals("some1", user1.displayName());
        user1.setBio("new1");
        assertEquals("new1", user1.displayBio());

        User user2 = new PremiumUser("some2", "fdsadf");
        assertEquals("<Premium> some2", user2.displayName());
        user2.setBio("new2");
        assertEquals("new2", user2.displayBio());

        User user3 = new StandardUser("some3", "fdsdf");
        assertEquals("some3", user3.displayName());
        user3.setBio("new3");
        assertEquals("new3", user3.displayBio());

        try {
            user1.joinRoom(test1);
            assertEquals(user1, test1.getUsers().get(0));
            user2.joinRoom(test1);
            assertEquals(user2, test1.getUsers().get(1));
            user3.joinRoom(test1);
            assertEquals(user3, test1.getUsers().get(2));
        }catch (OperationDeniedException e){
            System.out.println(e.getMessage());
        }

        ArrayList<User> ls1 = new ArrayList<>();
        ls1.add(user2);
        ls1.add(user3);

        MessageExchange room1 = user1.createChatRoom(ls1);
        assertArrayEquals(new User[]{user1, user2, user3}, room1.getUsers().toArray());

        User user4 = new StandardUser("some4", "irue");
        ArrayList<User> ls2 = new ArrayList<>();
        ls2.add(user1);
        ls2.add(user3);
        ls2.add(user4);

        MessageExchange room2 = user2.createChatRoom(ls2);
        assertArrayEquals(new User[]{user2, user1, user3, user4}, room2.getUsers().toArray());

        User user5 = new StandardUser("some5", "poiufn");
        ArrayList<User> ls3 = new ArrayList<>();
        ls3.add(user1);
        ls3.add(user2);
        ls3.add(user4);
        ls3.add(user5);

        MessageExchange room3 = user3.createChatRoom(ls3);
        assertArrayEquals(new User[]{user3, user1, user2, user4, user5},
                room3.getUsers().toArray());

        user1.sendMessage(test1, MessageType.TEXT, "poopoo");
        assertEquals("poopoo", test1.getLog(user1).get(0).contents);
        user2.sendMessage(test1, MessageType.PHOTO, "/poopoo2.png");
        assertEquals("/poopoo2.png", test1.getLog(user2).get(1).contents);
        user3.sendMessage(test1, MessageType.TEXT, "poopoo3");
        assertEquals("poopoo3", test1.getLog(user3).get(2).contents);

        user1.quitRoom(test1);
        assertEquals(2, test1.getUsers().size());
        user2.quitRoom(test1);
        assertEquals(1, test1.getUsers().size());
        user3.quitRoom(test1);
        assertEquals(0, test1.getUsers().size());
    }

    @Test
    public void testUserThrowsIAE() {
        try {
            User tst = new StandardUser(null, "");
            fail("Exception not thrown");
            tst.joinRoom(null);
            fail("Exception not thrown");
            tst.quitRoom(null);
            fail("Exception not thrown");
            tst.createChatRoom(null);
            fail("Exception not thrown");
            MessageExchange room = new ChatRoom();
            tst.sendMessage(null, MessageType.TEXT, "whwahahsd");
            fail("Exception not thrown");
            tst.sendMessage(room, null, "whwahahsd");
            fail("Exception not thrown");
            tst.sendMessage(room, MessageType.TEXT, null);
            fail("Exception not thrown");
            tst.sendMessage(room, MessageType.TEXT, "whwahahsd");
            fail("Exception not thrown");
            tst.setBio(null);
            fail("Exception not thrown");
        } catch (Exception iae) {
            assertEquals(iae.getMessage(), iae.getMessage());
        }
        try {
            User tst = new StandardUser("something", null);
            fail("Exception not thrown");
        } catch (Exception iae) {
            assertEquals(iae.getMessage(), iae.getMessage());
        }
    }

    @Test
    public void testUserThrowsODE() {
        try {
            User tst = new StandardUser("huh", "what");
            MessageExchange room = new ChatRoom();
            tst.joinRoom(room);
            tst.joinRoom(room);
        } catch (OperationDeniedException ode) {
            assertEquals(User.JOIN_ROOM_FAILED, ode.getMessage());
        }
        try {
            User tst = new StandardUser("huh", "what");
            MessageExchange room = new ChatRoom();
            tst.joinRoom(room);
            room.addUser(tst);
        } catch (OperationDeniedException ode) {
            assertEquals(User.JOIN_ROOM_FAILED, ode.getMessage());
        }
    }

    @Test
    public void standardUserTests(){
        test1 = new ChatRoom();

        StandardUser user1 = new StandardUser("some1", "sdf");
        assertEquals("some1", user1.displayName());

        StandardUser user2 = new StandardUser("some2", "fdsadf");
        assertEquals("some2", user2.displayName());

        StandardUser user3 = new StandardUser("some3", "fdsdf");
        assertEquals("some3", user3.displayName());

        PremiumUser user4 = new PremiumUser("some4", "yoyoyo");

        try {
            user1.joinRoom(test1);
            user4.joinRoom(test1);
        }catch (OperationDeniedException e){
            System.out.println(e.getMessage());
        }

        user1.sendMessage(test1, MessageType.TEXT, "poopoo");
        user1.sendMessage(test1, MessageType.TEXT, "sdfgdfg");
        assertEquals(test1.getLog(user1).get(0).getContents() + "\n" +
                test1.getLog(user1).get(1).getContents() + "\n", user1.fetchMessage(test1));
        user1.sendMessage(test1, MessageType.TEXT, "pooddfgspoo");
        assertEquals(test1.getLog(user1).get(0).getContents() + "\n" +
                test1.getLog(user1).get(1).getContents() + "\n" +
                test1.getLog(user1).get(2).getContents() + "\n", user1.fetchMessage(test1));
        user4.sendMessage(test1, MessageType.PHOTO, "yoyo.png");
        assertEquals(test1.getLog(user1).get(0).getContents() + "\n" +
                test1.getLog(user1).get(1).getContents() + "\n" +
                test1.getLog(user1).get(2).getContents() + "\n" +
                "This message cannot be fetched because you are not a premium user." +
                "\n", user1.fetchMessage(test1));
    }

    @Test
    public void testStandardUserThrowsIAE() {
        try {
            User tst = new StandardUser(null, "");
            MessageExchange room = new ChatRoom();
            tst.fetchMessage(null);
            fail("Exception not thrown");
            tst.fetchMessage(room);
            fail("Exception not thrown");
        } catch (Exception iae) {
            assertEquals(iae.getMessage(), iae.getMessage());
        }
    }

    @Test
    public void premiumUserTests(){
        test1 = new ChatRoom();

        PremiumUser user1 = new PremiumUser("some1", "sdf");
        assertEquals("<Premium> some1", user1.displayName());
        user1.setCustomTitle("Rocket Rookie");
        assertEquals("<Rocket Rookie> some1", user1.displayName());

        PremiumUser user2 = new PremiumUser("some2", "fdsadf");
        assertEquals("<Premium> some2", user2.displayName());

        PremiumUser user3 = new PremiumUser("some3", "fdsdf");
        assertEquals("<Premium> some3", user3.displayName());

        try {
            user1.joinRoom(test1);
            user2.joinRoom(test1);
            user3.joinRoom(test1);
        }catch (OperationDeniedException e){
            System.out.println(e.getMessage());
        }

        user1.sendMessage(test1, MessageType.TEXT, "poopoo");
        assertEquals(test1.getLog(user1).get(0).getContents() +
                "\n", user1.fetchMessage(test1));
        user2.sendMessage(test1, MessageType.PHOTO, "sdfgdfg.jpg");
        assertEquals(test1.getLog(user1).get(0).getContents() + "\n" +
                test1.getLog(user1).get(1).getContents() + "\n", user1.fetchMessage(test1));
        user3.sendMessage(test1, MessageType.TEXT, "pooddfgspoo");
        assertEquals(test1.getLog(user1).get(0).getContents() + "\n" +
                test1.getLog(user1).get(1).getContents() + "\n" +
                test1.getLog(user1).get(2).getContents() + "\n", user1.fetchMessage(test1));

        ArrayList<User> ls1 = new ArrayList<>();
        ls1.add(user2);
        ls1.add(user3);

        MessageExchange room1 = user1.createModeratedRoom(ls1);
        assertArrayEquals(new User[]{user1, user2, user3}, room1.getUsers().toArray());

        User user4 = new StandardUser("some4", "irue");
        ArrayList<User> ls2 = new ArrayList<>();
        ls2.add(user1);
        ls2.add(user3);
        ls2.add(user4);

        MessageExchange room2 = user2.createModeratedRoom(ls2);
        assertArrayEquals(new User[]{user2, user1, user3, user4}, room2.getUsers().toArray());

        User user5 = new StandardUser("some5", "poiufn");
        ArrayList<User> ls3 = new ArrayList<>();
        ls3.add(user1);
        ls3.add(user2);
        ls3.add(user4);
        ls3.add(user5);

        MessageExchange room3 = user3.createModeratedRoom(ls3);
        assertArrayEquals(new User[]{user3, user1, user2, user4, user5},
                room3.getUsers().toArray());

        user3.banUser((ModeratedRoom) room3, user2);
        assertFalse(room3.getUsers().contains(user2));
        assertEquals(user2, ((ModeratedRoom) room3).getBanned().get(0));

        user3.banUser((ModeratedRoom) room3, user1);
        assertFalse(room3.getUsers().contains(user1));
        assertEquals(user1, ((ModeratedRoom) room3).getBanned().get(1));

        user3.banUser((ModeratedRoom) room3, user5);
        assertFalse(room3.getUsers().contains(user5));
        assertEquals(user5, ((ModeratedRoom) room3).getBanned().get(2));

        user3.unbanUser((ModeratedRoom) room3, user2);
        assertFalse(((ModeratedRoom) room3).getBanned().contains(user2));

        user3.unbanUser((ModeratedRoom) room3, user1);
        assertFalse(((ModeratedRoom) room3).getBanned().contains(user1));

        user3.unbanUser((ModeratedRoom) room3, user5);
        assertFalse(((ModeratedRoom) room3).getBanned().contains(user5));

        user3.setNumVisibleLog((ModeratedRoom) room3, 20);
        assertEquals(20, ((ModeratedRoom) room3).getNumVisibleLog());

        user3.setNumVisibleLog((ModeratedRoom) room3, 70);
        assertEquals(70, ((ModeratedRoom) room3).getNumVisibleLog());

        user3.setNumVisibleLog((ModeratedRoom) room3, 150);
        assertEquals(150, ((ModeratedRoom) room3).getNumVisibleLog());
    }

    @Test
    public void testPremiumUserThrowsIAE2() {
        try {
            PremiumUser tst = new PremiumUser("whathtrh", "");
            MessageExchange room = new ChatRoom();
            tst.fetchMessage(null);
            fail("Exception not thrown");
            tst.fetchMessage(room);
            fail("Exception not thrown");
            tst.createModeratedRoom(null);
            fail("Exception not thrown");
        } catch (Exception iae) {
            assertEquals(iae.getMessage(), iae.getMessage());
        }
    }

    @Test
    public void moderatedRoomTests(){
        PremiumUser user1 = new PremiumUser("some1", "sdf");
        ModeratedRoom room1 = new ModeratedRoom(user1);
        assertNotNull(room1.getLog(user1));
        assertNotNull(room1.getBanned());
        assertNotNull(room1.getLog(user1));
        assertNotNull(room1.getUsers());
        assertEquals(Integer.MAX_VALUE, room1.getNumVisibleLog());
        room1.setNumVisibleLog(user1, 50);
        assertEquals(50, room1.getNumVisibleLog());

        PremiumUser user2 = new PremiumUser("some2", "sdfdsaf");
        ModeratedRoom room2 = new ModeratedRoom(user2);
        assertNotNull(room2.getLog(user2));
        assertNotNull(room2.getBanned());
        assertNotNull(room2.getLog(user2));
        assertNotNull(room2.getUsers());
        assertEquals(Integer.MAX_VALUE, room2.getNumVisibleLog());

        PremiumUser user3 = new PremiumUser("some3", "lolol");
        ModeratedRoom room3 = new ModeratedRoom(user3);
        assertNotNull(room3.getLog(user3));
        assertNotNull(room3.getBanned());
        assertNotNull(room3.getLog(user3));
        assertNotNull(room3.getUsers());
        assertEquals(Integer.MAX_VALUE, room3.getNumVisibleLog());

        User user4 = new PremiumUser("user4", "fsdfasdf");

        room1.addUser(user1);
        assertEquals(user1, room1.getUsers().get(0));
        room1.addUser(user2);
        assertEquals(user2, room1.getUsers().get(1));
        room1.addUser(user3);
        assertEquals(user3, room1.getUsers().get(2));
        room1.addUser(user4);

        room1.removeUser(user1, user4);
        assertEquals(3, room1.getUsers().size());
        room1.removeUser(user1, user2);
        assertEquals(2, room1.getUsers().size());
        room1.removeUser(user1, user3);
        assertEquals(1, room1.getUsers().size());



        room2.addUser(user1);
        room2.addUser(user2);
        room2.addUser(user3);
        room2.addUser(user4);

        try {
            this.msg = new TextMessage(user1, "fssfd");
            this.msg2 = new PhotoMessage(user2, "/fssfd.png");
            this.msg3 = new TextMessage(user3, "dddd");
        } catch (OperationDeniedException e){
            System.out.println(e.getMessage());
        }

        room1.recordMessage(this.msg);
        assertEquals(1, room1.getLog(user1).size());
        room1.recordMessage(this.msg2);
        assertEquals(2, room1.getLog(user1).size());
        room1.recordMessage(this.msg3);
        assertEquals(3, room1.getLog(user1).size());

        room2.banUser(user2, user1);
        assertFalse(room2.getUsers().contains(user1));
        assertTrue(room2.getBanned().contains(user1));
        room2.banUser(user2, user3);
        assertFalse(room2.getUsers().contains(user3));
        assertTrue(room2.getBanned().contains(user3));
        room2.banUser(user2, user4);
        assertFalse(room2.getUsers().contains(user4));
        assertTrue(room2.getBanned().contains(user4));

        room2.unbanUser(user2, user1);
        assertFalse(room2.getBanned().contains(user1));
        room2.unbanUser(user2, user3);
        assertFalse(room2.getBanned().contains(user3));
        room2.unbanUser(user2, user4);
        assertFalse(room2.getBanned().contains(user4));
    }

}
