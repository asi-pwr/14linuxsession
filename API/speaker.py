#coding=utf8
from db import db

class Speaker(db.Model):
	id = db.Column(db.Integer, primary_key=True)
	name = db.Column(db.String(25))
	description = db.Column(db.String(500))
	img_url = db.Column(db.String(60))

	
	def __init__(self, id_, name, description, img_url):
		self.id = id_
		self.name = name
		self.description = description
		self.img_url = img_url

	def __str__(self):
		return 'Speaker #{}: {}'.format(self.id, self.name)
		
	def to_dict(self):
		return {'id': self.id, 'name': self.name, 'description': self.description, 'img_url': self.img_url}

	@staticmethod
	def get(id_ = None):
		return _speakers if id_ is None else Speaker.query.filter_by(id=id_).first()

_speakers = [
	Speaker(1, u'Krzysztof Opasiak', u'Krzysztof is a PhD student at Warsaw University of Technology. He works as Kernel and System Developer at Samsung R&D Institute Poland. Since 2013 involved in USB support development in Tizen OS.\nMaintainer of libusbgx - library for USB gadgets management through ConfigFS. Open Source enthusiast and speaker at several Linux and Open Source Conferences.', 'http://14.sesja.linuksowa.pl/img/ludzie/opasiak.jpg'),
	Speaker(2, u'Marcin Tomków', u'Senior Lead Administrator systemów *NIX, GNU/Linux w firmie NetworkedAssets Sp. z o.o. Dwunastoletnie doświadczenie w branży IT. Entuzjasta i propagator wolnego oprogramowania. Interesuje się wykorzystaniem i implementacją nowoczesnych technologii w budowie centrum danych i administracji serwerami. Na bieżąco śledzi najnowsze trendy w tej dziedzinie. Apple Fanboy.', 'http://14.sesja.linuksowa.pl/img/ludzie/tomkow.jpg'),
	Speaker(3, u'Rafał Wysocki', u'Opiekun podsystemów PM i ACPI w jądrze Linuksa. Pracuje w Intel Open Source Technology Center, gdzie zajmuje się głównie rozwiązywaniem problemów związanych z jądrem Linuksa.\nW roku 1996 ukończył studia magisterskie z fizyki teoretycznej na Wydziale Fizyki Uniwersytetu Warszawskiego, a w roku 2002 uzyskał stopień naukowy doktora w dziedzinie fizyki na tym samym wydziale.', 'http://14.sesja.linuksowa.pl/img/ludzie/wysocki.jpg'),
	Speaker(4, u'Marcin Słoniewski', u'Absolwent kierunku Automatyka i Robotyka na wydziale Elektroniki Politechniki Wrocławskiej. Obecnie zatrudniony w firmie Pruftechnik Technology, gdzie zajmuje się rozwojem systemów wbudowanych dla diagnostyki maszyn przemysłowych.', 'http://14.sesja.linuksowa.pl/img/ludzie/sloniewski.jpg'),
	Speaker(5, u'Stanisław Klekot', u'Sezonowany administrator/programista systemowy. Większą część czasu poświęca na budowanie narzędzi, których później używa do budowania narzędzi i zarządzania serwerami.', 'http://14.sesja.linuksowa.pl/img/ludzie/klekot.jpg'),
	Speaker(6, u'Fabian Thorns', u'Fabian Thorns is the Director of Certification Development at Linux Professional Institute, LPI. He is M.Sc. Business Information Systems, a regular speaker at open source events and the author of numerous articles and books.', ''),
	Speaker(8, u'Bartłomiej Piotrowski, Michał Rostecki', u'Bartłomiej Piotrowski - Złota rączka w dystrybucji Arch Linux, zawodowo inżynier wdrożeń OpenStacka.\nMichał Rostecki - Developer, widoczny głównie w community Kubernetesa, dawniej udzielający się w OpenStacku', 'http://14.sesja.linuksowa.pl/img/ludzie/piotrowski.jpg'),
	Speaker(9, u'Maciej "docent" Lasyk', u'Absolwent AGH w Krakowie, administrator w firmie Ocado w wolnym czasie rozwijający dystrybuję Fedora oraz zapalony cyklista.', 'http://14.sesja.linuksowa.pl/img/ludzie/lasyk.jpg'),
	Speaker(10, u'Jakub "Siewca" Juszczakiewicz', u'Zażarty użytkownik systemów operacyjnych GNU/Linux, programista z zawodu, admin z zamiłowania. Po za IT lubi jeździć na rowerze i na nartach, oraz pływać pod żaglami jak i wpław.', 'http://14.sesja.linuksowa.pl/img/ludzie/siewca.jpg'),
	Speaker(11, u'Kajetan "kitor" Krykwiński', u'Inżynier integracji oprogramowania w firmie Nokia, były wiceprezes Akademickiego Stowarzyszenia Informatycznego, absolwent PWR. \nLinuksa na serwerach używa od kilkunastu lat, jednocześnie nie znosząc go jako system desktopowy.', ''),
	Speaker(12, u'Szymon Datko', u'DevOps at OVH.pl - daily, PhD student at Wrocław University of Science and Technology - occasionally, Dungeon Master - in free time, IKEA\'s furniture assembling lover - anytime :-)', 'http://14.sesja.linuksowa.pl/img/ludzie/datko.jpg'),
	Speaker(13, u'Marcin Gudajczyk', u'Pracuję dla NOKII od ponad pięciu lat w zakresie tematów związanych z SCM. Interesuję się kilkoma tematami, w szczególności astronomią, książkami sci-fi, historią oraz grami video.', 'http://14.sesja.linuksowa.pl/img/ludzie/gudajczyk.jpg'),
	Speaker(14, u'Patryk Czajkowski', u'Elektronik, DevOps. Zawodowo wieloletnio zwiazany z IoT od strony SW jak i HW, Aktywny uzytkownik Yocto. W wolnych chwilach pasjonat elektroniki i druku 3D', 'http://14.sesja.linuksowa.pl/img/ludzie/czajkowski.jpg'),
	Speaker(15, u'Łukasz Gardoń', u'Student Politechniki Wrocławskiej pajsonat Linuksa, wolnego oprogramowania oraz systemów wbudowanych. Miłośnik wypraw rowerowych.', 'http://14.sesja.linuksowa.pl/img/ludzie/gardon.jpg')
]



