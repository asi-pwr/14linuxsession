#coding=utf8
from db import db

class Lecture(db.Model):
	id = db.Column(db.Integer, primary_key=True)
	title = db.Column(db.String(50))
	description = db.Column(db.String(500))
	day = db.Column(db.Integer)
	start_time = db.Column(db.String(5))
	end_time = db.Column(db.String(5))
	speaker_id = db.Column(db.Integer, db.ForeignKey('speaker.id'))
	speaker = db.relationship('Speaker', backref=db.backref('lecture'))

	def __init__(self, id_, speaker_id, title, description, day, start_time, end_time):
		self.id = id_
		self.speaker_id = speaker_id
		self.title = title
		self.description = description
		self.day = day
		self.start_time = start_time
		self.end_time = end_time

	def __str__(self):
		return 'Lecture #{}: {}'.format(self.id, self.title)
		
	def to_dict(self):
		return {'id': self.id, 'title': self.title, 'description': self.description, 'speaker_id': self.speaker_id, 
				'day': self.day, 'start_time': self.start_time, 'end_time': self.end_time}

	@staticmethod
	def get(id_ = None):
		return _lectures if id_ is None else Lecture.query.filter_by(id=id_).first()

_lectures = [
	Lecture(1, 4, u'Yocto Project - stwórzmy własną dystrybucję Linuksa dla systemu wbudowanego!', u'Projekt Yocto jest otwartoźródłowym środowiskiem do tworzenia własnej dystrybucji Linuksa. Dzięki wygodzie korzystania z niego w dużych zespołach oraz wspieraniu przez niego wiele architektur procesorów jest obecnie szeroko stosowany w przemyśle do tworzenia systemów wbudowanych.\n\nPodczas tej prelekcji przedstawione zostaną podstawy pracy w środowisku oferowanym przez Projekt Yocto oraz jego wady i zalety. Wszystko to pokazane będzie na przykładzie “wypiekania” na platformę Raspberry Pi własnej dystrybucji Linuksa, która zawiera napisaną przez nas testową aplikację.', 1, u'10:00', u'11:00'), 
	Lecture(2, 1, u'Usually Slightly Broken (USB) devices and what you can do with them?', u'USB is definitely the most popular external interface. Thousands of people are using it every day and many of them have problems with it. Driver not found, incorrect driver bound, kernel oops are just examples of common problems which we are all facing. How to solve them or at least debug? If you’d like to find out, then this talk is exactly for you!\n\nWe start with a gentle introduction to the USB protocol itself. Then standard Linux host side infrastructure will be discussed. How drivers are chosen? How can we modify matching rules of a particular driver? That\'s only couple of questions which will be answered in this part. Final part is an introduction to USB communication sniffing. Krzysztof will show how to monitor and analyze USB traffic without expensive USB analyzers.', 1, u'11:00', u'12:00'), 
	Lecture(3, 5, u'Przyrządzanie koszernych paczek RPM i DEB dla zajętych', u'Pakiety RPM i DEB nie rosną na drzewach, więc zanim znajdą się w dystrybucji, ktoś je musi zbudować i opublikować. Wykład pokaże, jak przygotować paczkę binarną i jak ją dostarczyć za pomocą repozytoriów APT i Yum.', 1, u'12:00', u'13:00'), 
	Lecture(4, 2, u'Jak uprzykrzyć życie włamywacza wykorzystując narzędzia systemów GNU/Linux', u'Zarządy firm z reguły reagują "wysypką" na potrzebę inwestycji w bezpieczeństwo organizacji. "Security" nie bez powodu kojarzone jest z dużymi wydatkami na szkolenia, sprzęt i technologię których, koniec końców, nikt nie rozumie, a co gorsze, nie potrafi poprawnie skonfigurować.\n\nChciałbym pokazać, jak można wykorzystać wachlarz narzędzi zawartych w dystrybucjach GNU/Linux oraz kliku zagrywek socjotechnicznych, aby bez potrzeby ponoszenia sporych kosztów, uprzykrzyć życie potencjalnego włamywacza.', 1, u'14:00', u'15:00'), 
	Lecture(5, 8, u'Flatpak - nowoczesna dystrybucja programów', u'Klasyczny model dystrybucji oprogramowania, scentralizowany w samych dystrybucjach Linuksa, nie jest pozbawiony wad. Nieograniczone uprawnienia programów, nieistniejący wpływ programistów na opiekunów, ignorowanie małych projektów to tylko niektóre z problemów, które można wytknąć standardowym dystrybucjom. Przez ostatnie lata pojawiło się kilka narzędzi, starających się zagarnąć swój kawałek tortu w temacie uniwersalnych pakietów.\n\nDlaczego Flatpak wydaje się być faworytem, jak działa, w jaki sposób ułatwia to życie klasycznym dystrybucjom i zwykłym użytkowników oraz jak stworzyć własny pakiet – na te pytanie odpowie nasza prezentacja.', 1, u'15:00', u'16:00'), 
	Lecture(6, 9, u'Systemd: Poza strefą komfortu', u'W trakcie tej prezentacji chciałbym wam pokazać do czego można użyć systemd poza funkcjami, z których jest najbardziej popularny (vide: init - system). Część tych dodatkowych funkcji jest mało popularna lub też zastępuje dotychczas używane i znane rozwiązania - stąd też tytułowe nawiązanie do opuszczenia strefy komfortu. Postaram się omówić w jaki sposób korzystać z dystrybucji zawierających systemd w Google Cloudzie czy AWSie.', 1, u'16:00', u'17:00'), 
	Lecture(7, 6, u'DevOps Tools Explained: Update Your Arsenal', u'Buzzword like cloud, container, deployment, continuous delivery or configuration automation are rampant in the media these days. This keynote explains what\'s behind these terms, how they relate to each other and what they mean for the daily job of system administrators and software developers. We will also discuss which technologies provide an actual benefit for technicians and how to approach and learn them.', 2, u'10:00', u'11:00'), 
	Lecture(8, 3, u'Linux i oszczędzanie energii', u'Mechanizmy zwiazane z oszczędzaniem energii, a ściślej mówiąc z zapobieganiem traceniu energii bez potrzeby, są ważną częścią Linuksa od ponad 10 lat. Ogólnie można podzielić je na dwie grupy, te działające jednocześnie na cały system (system-wide PM) oraz te operujące pojedynczymi składnikami systemu, takimi jak procesory i urządzenia wejścia-wyjścia, w stanie aktywności (working-state PM). Do pierwszej grupy należą usypianie systemu (system suspend) i hibernacja, natomiast druga grupa obejmuje między innymi skalowanie wydajności procesorów (CPU performance scaling) oraz wykrywanie nieaktywności i wprowadzanie nieaktywnych (idle) składników systemu do stanów o ograniczonym poborze mocy (low-power states). Mechanizmy z drugiej grupy, poza swoim głównym zastosowaniem, mogą także służyć do zapobiegania przegrzewaniu się urządzeń, bądź zapobiegania przekraczaniu budżetów energetycznych lub limitów poboru mocy przy jednoczesnym zapewnieniu wykonania określonych operacji w zadanym czasie.\n\nW prezentacji przedstawię przegląd mechanizmów z dziedziny power management znajdujących się w jądrze Linuksa oraz omówię ich działanie i sposoby wykorzystania, a także wyzwania, z jakimi ta dziedzina musi się mierzyć w związku z obecnymi trendami w rozwoju technologii.', 2, u'11:00', u'12:00'), 
	Lecture(9, 11, u'Windows 10 uruchamia natywne programy z Linuksa. Bash on Ubuntu on Windows', u'W zeszłym roku Microsoft zaskoczył wiele osób ogłaszając Bash on Ubuntu on Windows. Projekt obiecywał uruchamianie niezmodyfikowanych linuksowych narzędzi deweloperskich w systemie Windows 10, bez użycia wirtualnej maszyny.\n\nJednak czy tylko narzędzia deweloperskie? Jak działa i co właściwie potrafi Windows Subsystem for Linux? W końcu mowa tu o >>niezmodyfikowanych<< programach.\n\nPrelekcja wprowadzi w rozwiązania techniczne stojące za WSL, zademonstruje jego możliwości z punktu widzenia dewelopera jak i zwykłego użytkownika oraz odpowie na pytanie czy Microsoft łamie licencję GNU GPL nie udostępniając kodów źródłowych jądra systemu Windows ;)', 2, u'12:00', u'13:00'), 
	Lecture(10, 12, u'CDS - simple, scalable, powerful CI/CD solution', u'Nowadays, the automation is a compulsory part of every IT project, either in big company or just small start-up. Reliable and scalable solution for tasks automation is always desirable - and this is what the CDS was designed for. During the presentation, I would like to introduce the CDS - describe its basic concepts, as well as how to set and use it. Please, join for an overview!', 2, u'14:00', u'15:00'), 
	Lecture(11, 13, u'The practical aspects of YOCTO', u'Nie chcę podchodzić do tematu YOCTO z typowym opisem detali technicznych. Zamiast tego, korzystając z mojego doświadczenia w zakresie konfiguracji i integracji oprogramowania, chciałbym zaprezentować kilka anegdot, przedstawiających zyski jakie można osiągnąć z użycia YOCTO w projekcie oraz jakie problemy YOCTO pomoże faktycznie rozwiązać.', 2, u'15:00', u'16:00'), 
	Lecture(12, 10, u'Interfejsy sieciowe w Linuksie - wprowadzenie do czarnej magii', u'Podstawowe interfejsy sieciowe w Linuksie dotyczą fizycznych kart sieciowych, modemów, jak i tzw. pętli zwrotnej. Trochę bardziej zaawansowane wykorzystanie pozwala uruchomić VPNy i VLANy. Ale na tym się nie kończy. Mosty, czy interfejsy połączeń splątanych pozwalają uzyskać ciekawe efekty w działaniu sieci. To będzie małe wprowadzenie to tej części magii sieci na Linuksie.', 2, u'16:00', u'17:00')
]