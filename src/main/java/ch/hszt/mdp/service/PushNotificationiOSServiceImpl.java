package ch.hszt.mdp.service;

import java.util.ArrayList;

import urbanairship.Device;
import urbanairship.Push;
import urbanairship.UrbanAirshipClient;
import ch.hszt.mdp.domain.User;

public class PushNotificationiOSServiceImpl implements PushNotificationService {

	@Override
	public void addAsFriend(User user) {
		UrbanAirshipClient client = new UrbanAirshipClient("D5Y-rVatQYezvZWP4NEAZA", "97yqnMs1QO2csecE_VM1VQ");

		Device device = new Device();

		device.setiOSDeviceToken("56388792dcfaa3c4a08cdbafee90467607c44c8196a021beebc5ae7988164eaa");

		client.register(device);
		Push p = new Push();

		p.setMessage(user.getPrename() + " " + user.getSurname() + " added you as friend.");
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.add("56388792dcfaa3c4a08cdbafee90467607c44c8196a021beebc5ae7988164eaa");
		p.setDeviceTokens(tokens);

		client.sendPushNotifications(p);
	}
}
