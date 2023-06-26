.DEFAULT_GOAL := build-run

clean:
	make -C app clean

build:
	make -C app build

install:
	make -C app install

run-dist:
	make -C app run-dist

runjson:
	make -C app runjson

runjsonnested:
	make -C app runjsonnested

runyml:
	make -C app runyml

runymlnested:
	make -C app runymlnested

runjsonplain:
	make -C app runjsonplain

runjsonnestedplain:
	make -C app runjsonnestedplain

runymlplain:
	make -C app runymlplain

runymlnestedplain:
	make -C app runymlnestedplain

runjsontojson:
	make -C app runjsontojson

runymltojson:
	make -C app runymltojson

runjsonnestedtojson:
	make -C app runjsonnestedtojson

runymlnestedtojson:
	make -C app runymlnestedtojson

runhelp:
	make -C app runhelp

lint:
	make -C app lint

test:
	make -C app test

report:
	make -C app report

update-deps:
	make -C app update-deps

build-run: build run

.PHONY: build

