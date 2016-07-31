package me.nimavat.lapsi.admin

import me.nimavat.core.AdminSection

@AdminSection(name="Dashboard", order=0)
class HomeController {
	static defaultAction = "dashboard"
	static namespace = "admin"

	def dashboard() {

	}
}
