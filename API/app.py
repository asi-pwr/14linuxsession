#!flask/bin/python2.7
#coding=utf8

from flask import Flask, jsonify, abort, make_response, request
from flask.json import JSONEncoder
from flask_sqlalchemy import SQLAlchemy
from constants import credentials
from speaker import Speaker
from lecture import Lecture
from sponsor import Sponsor
from db import db


#API SETUP
class MyJSONEncoder(JSONEncoder):
    def default(self, obj):
        if isinstance(obj, Speaker) or isinstance(obj, Lecture) or isinstance(obj, Sponsor):
            return obj.to_dict()
        return super(MyJSONEncoder, self).default(obj)

def setup_database_contents():
	if not Speaker.query.all():
		print 'speakers db rebuilt'
		for s in Speaker.get():
			db.session.add(s)
		db.session.commit()

	if not Lecture.query.all():
		print 'lectures db rebuilt'
		for l in Lecture.get():
			db.session.add(l)
		db.session.commit()

	if not Sponsor.query.all():
		print 'sponsors db rebuilt'
		for l in Sponsor.get():
			db.session.add(l)
		db.session.commit()

app = Flask(__name__)
app.json_encoder = MyJSONEncoder
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///sesja.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = 'False'

db.app = app
db.init_app(app)
db.create_all()
setup_database_contents()
speakers = Speaker.query.all()
lectures = Lecture.query.all()
sponsors = Sponsor.query.all()

@app.errorhandler(404)
def not_found(error):
	return make_response(jsonify({'error': 'Not found'}), 404)


#PUBLIC ROUTES
@app.route('/speakers', methods=['GET'])
def get_speakers():
	if 'lecture_id' in request.args:
		_lecture_id = int(request.args['lecture_id'])
		_lecture = Lecture.get(_lecture_id)
		if _lecture:
			_speaker = _lecture.speaker
			if _speaker:
				return jsonify({
					'speaker': _speaker,
					'lecture': _lecture
					})
			else: abort(404)
		else: abort(404)
	return jsonify({'speakers':speakers})

@app.route('/speakers/<int:speaker_id>')
def get_speaker(speaker_id):
	speaker = Speaker.get(speaker_id)
	if not speaker:
		abort(404)
	return jsonify({'speaker': speaker})

@app.route('/lectures', methods=['GET'])
def get_lectures():
	if 'speaker_id' in request.args:
		_speaker_id = int(request.args['speaker_id'])
		_speaker = Speaker.get(_speaker_id)
		if _speaker: 
			_lecture = _speaker.lecture
			if _lecture:	
				return jsonify({
					'speaker': _speaker,
					'lecture': _lecture
					})
			else: abort(404)
		else: abort(404)
	return jsonify({'lectures':lectures})

@app.route('/lectures/<int:lecture_id>')
def get_lecture(lecture_id):
	lecture = Lecture.get(lecture_id)
	if not lecture:
		abort(404)
	return jsonify({'lecture': lecture})



@app.route('/sponsors', methods=['GET'])
def get_sponsors():
	return jsonify({'sponsors':sponsors})


if __name__ == "__main__":
	app.run(host="127.0.0.1",port=3007)