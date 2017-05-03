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
	Speaker(



