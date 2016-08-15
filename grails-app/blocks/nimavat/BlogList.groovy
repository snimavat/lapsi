package nimavat

import me.nimavat.lapsi.Block
import me.nimavat.lapsi.LapsiPage

class BlogList implements Block {

	String name = "blog-list"

	@Override
	public Map getModel() {
		List<LapsiPage> blogs = LapsiPage.withCriteria {
			ne 'uri', 'home-page'
		}
		return [blogs:blogs]
	}

}

