package org.mutiaraiman.prayers.actions;

import org.apache.struts2.ServletActionContext;
import org.mutiaraiman.prayers.Prayer;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.PostUpdate;

public class FacebookPagePostUtil {
	
	public static void post(Prayer prayer){
		Facebook facebook = (Facebook) ServletActionContext.getRequest().getSession().getAttribute("facebook");
		if (facebook != null) {
			try {
				StringBuilder sb = new StringBuilder(prayer.getTitle()+"\n");
				if (prayer.getContent().length() >= 200) {
					sb.append(prayer.getContent().substring(0, 200)+"...\n");
				}else{
					sb.append(prayer.getContent());
				}
				String namespace = "";
				switch (prayer.getType()) {
				case PRAYER:
					namespace = "prayers";
					break;
				case REFLECTION:
					namespace = "reflections";
					break;
				case PRAISE_AND_WORSHIP:
					namespace = "worship";
					break;
				case STORIES:
					namespace = "stories";
					break;
				case QUOTE:
					namespace = "quote";
					break;
				}
				sb.append("\n lihat semua http://www.mutiara-iman.org/module/"+namespace+"#"+prayer.getId());
				PostUpdate postUpdate = new PostUpdate(sb.toString());
				facebook.postFeed("201793193210386", postUpdate);
			} catch (FacebookException e) {
				e.printStackTrace();
			}
		}
	}
}
