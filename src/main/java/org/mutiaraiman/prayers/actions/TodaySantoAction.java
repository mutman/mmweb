package org.mutiaraiman.prayers.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;
import org.mutiaraiman.prayers.Prayer;

@Action("today")
@Namespace("/module")
@Results({ @Result(name = DefaultAction.INDEX, type = "freemarker", location = "/org/mutiaraiman/prayers/view/prayers/prayers-list.ftl") })
public class TodaySantoAction extends DefaultPrayerController {

	private static final long serialVersionUID = -6571163240867896216L;

	public String index() {
		Prayer p = prayerService.getTodayStory();
		if (p != null) {
			model = p;
		} else {
			model = new Prayer();
		}
		return INDEX;
	}
}
