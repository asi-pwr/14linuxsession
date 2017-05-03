#coding=utf8
from db import db

class Sponsor(db.Model):
	id = db.Column(db.Integer, primary_key=True)
	name = db.Column(db.String(25))
	img_url = db.Column(db.String(60))
	link = db.Column(db.String(60))
	category = db.Column(db.String(15))

	def __init__(self, id_, name, link, img_url, category):
		self.id = id_
		self.name = name
		self.link = link
		self.img_url = img_url
		self.category = category

	def __str__(self):
		return 'Sponsor #{}: {}'.format(self.id, self.name)
		
	def to_dict(self):
		return {'id': self.id, 'name': self.name, 'link': self.link, 'img_url': self.img_url, 'category': self.category}

	@staticmethod
	def get(id_ = None):
		return _sponsors if id_ is None else Sponsor.query.filter_by(id=id_).first()

_sponsors = [
	Sponsor(1, u'Nokia', u'http://nokiawroclaw.pl/', u'http://14.sesja.linuksowa.pl/img/loga/logo-nokia.svg', 'sponsor'),
	Sponsor(2, u'OVH.pl', u'http://ovh.pl/', u'http://14.sesja.linuksowa.pl/img/loga/logo-ovh.png', 'sponsor'),
	Sponsor(3, u'RST', u'http://rst.com.pl/', u'http://14.sesja.linuksowa.pl/img/loga/logo-rst.png', 'sponsor'),
	Sponsor(4, u'LPI', u'https://www.lpi.org/', u'http://14.sesja.linuksowa.pl/img/loga/logo-lpi.png', 'sponsor'),
	Sponsor(5, u'Global Logic', u'https://www.globallogic.com/pl/', u'http://14.sesja.linuksowa.pl/img/loga/logo-gl.svg', 'sponsor'),
	Sponsor(6, u'Tieto', u'http://tieto.pl/', u'http://14.sesja.linuksowa.pl/img/loga/logo-tieto.png', 'sponsor'),
	Sponsor(7, u'Magazyn Programista', u'https://programistamag.pl/', u'http://14.sesja.linuksowa.pl/img/loga/patron-programista.png', 'patron'),
	Sponsor(8, u'Chip', u'http://chip.pl/', u'http://14.sesja.linuksowa.pl/img/loga/patron-chip.png', 'patron'),
	Sponsor(9, u'Hacker Space Wrocław', u'https://www.hswro.org/', u'http://14.sesja.linuksowa.pl/img/loga/patron-hs.png', 'patron'),
	Sponsor(10, u'PDA site', u'http://pdasite.pl/', u'http://14.sesja.linuksowa.pl/img/loga/patron-pdasite.png', 'patron'),
	Sponsor(11, u'ASI', u'http://asi.wroclaw.pl/', u'http://14.sesja.linuksowa.pl/img/loga/logo-asi.png', 'org'),
	Sponsor(12, u'NOMAD', u'http://wireless-group.pwr.wroc.pl/', u'http://14.sesja.linuksowa.pl/img/loga/logo-nomad.png', 'org'),
	Sponsor(13, u'Wireless Group', u'http://wireless-group.pwr.wroc.pl/', 'http://14.sesja.linuksowa.pl/img/loga/logo-wg.png', 'org'),
	Sponsor(14, u'Manus', u'http://manus.pl/', u'http://14.sesja.linuksowa.pl/img/loga/logo-manus.png', 'org'),
	Sponsor(15, u'Politechnika Wrocławska', u'http://www.pwr.edu.pl/', u'http://14.sesja.linuksowa.pl/img/loga/logo-pwr.png', 'org')
]


