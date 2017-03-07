#!flask/bin/python
from flask import Flask, jsonify, abort, make_response, request
from flask_httpauth import HTTPBasicAuth
from flask.json import JSONEncoder
from flask_sqlalchemy import SQLAlchemy
from constants import credentials
from speaker import Speaker
from lecture import Lecture
from db import db


#API SETUP
class MyJSONEncoder(JSONEncoder):
    def default(self, obj):
        if isinstance(obj, Speaker) or isinstance(obj, Lecture):
            return obj.to_dict()
        return super(MyJSONEncoder, self).default(obj)

app = Flask(__name__)
app.json_encoder = MyJSONEncoder
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///sesja.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = 'False'
db.app = app
db.init_app(app)
auth = HTTPBasicAuth()
db.create_all()
speakers = Speaker.query.all()
lectures = Lecture.query.all()

@auth.get_password
def get_pw(username):
    if username in credentials:
        return credentials.get(username)
    return None

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


#ADMIN PANEL
@app.route('/admin', methods=['GET'])
@auth.login_required
def get_admin_dashboard():
	return jsonify({'credentials':credentials})

def setup_database():
	if not speakers:
		print 'speakers db rebuilt'
		for s in Speaker.get():
			db.session.add(s)
		db.session.commit()

	if not lectures:
		print 'speakers db rebuilt'
		for l in Lecture.get():
			db.session.add(l)
		db.session.commit()

if __name__ == "__main__":
	setup_database()
	app.run(host="127.0.0.1",port=3007)