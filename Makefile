build:
	make -C app build
checkstyleMain:
	make -C app lint
test:
	make -C app test