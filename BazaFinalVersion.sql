PGDMP                          x            Projekt    12.1    12.1 I    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    35078    Projekt    DATABASE     �   CREATE DATABASE "Projekt" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Polish_Poland.1250' LC_CTYPE = 'Polish_Poland.1250';
    DROP DATABASE "Projekt";
                postgres    false            �            1259    35079    dzien    TABLE     P   CREATE TABLE public.dzien (
    "Id_dnia" integer NOT NULL,
    "Dzien" text
);
    DROP TABLE public.dzien;
       public         heap    postgres    false            �            1259    35085    events    TABLE     s   CREATE TABLE public.events (
    id_event integer NOT NULL,
    event text,
    data date,
    id_klasy integer
);
    DROP TABLE public.events;
       public         heap    postgres    false            �            1259    35091    godzina    TABLE     �   CREATE TABLE public.godzina (
    "Id_godzina" integer NOT NULL,
    "Id_dnia" integer NOT NULL,
    "Ktora_godzina" text,
    id_godziny integer NOT NULL
);
    DROP TABLE public.godzina;
       public         heap    postgres    false            �            1259    35097    klasa    TABLE     i   CREATE TABLE public.klasa (
    id_klasy integer NOT NULL,
    nazwa_klasy text,
    rok_szkolny text
);
    DROP TABLE public.klasa;
       public         heap    postgres    false            �           0    0    TABLE klasa    COMMENT     V   COMMENT ON TABLE public.klasa IS 'zawierająca podstawowe informacje na temat klasy';
          public          postgres    false    205            �            1259    35103    lekcja    TABLE     �   CREATE TABLE public.lekcja (
    id_lekcji integer NOT NULL,
    id_godziny integer,
    id_nauczyciela integer,
    id_przedmiotu integer,
    sala integer,
    id_klasy integer
);
    DROP TABLE public.lekcja;
       public         heap    postgres    false            �            1259    35106    nauczyciele    TABLE     �   CREATE TABLE public.nauczyciele (
    id_nauczyciela integer NOT NULL,
    imie text,
    nazwisko text,
    email text,
    haslo text,
    id_przedmiotu integer
);
    DROP TABLE public.nauczyciele;
       public         heap    postgres    false            �            1259    35112    oceny    TABLE     �   CREATE TABLE public.oceny (
    id_przedmiotu integer,
    data date,
    id_oceny integer NOT NULL,
    rodzaj_oceny text,
    ocena integer,
    id_ucznia integer,
    semestr integer
);
    DROP TABLE public.oceny;
       public         heap    postgres    false            �            1259    35118    plan    TABLE     W   CREATE TABLE public.plan (
    "Id_klasy" integer NOT NULL,
    "Id_lekcji" integer
);
    DROP TABLE public.plan;
       public         heap    postgres    false            �            1259    35121 
   przedmioty    TABLE     b   CREATE TABLE public.przedmioty (
    id_przedmiotu integer NOT NULL,
    nazwa_przedmiotu text
);
    DROP TABLE public.przedmioty;
       public         heap    postgres    false            �            1259    35127    relacje    TABLE     a   CREATE TABLE public.relacje (
    id_ucznia integer NOT NULL,
    id_rodzica integer NOT NULL
);
    DROP TABLE public.relacje;
       public         heap    postgres    false            �            1259    35130    rodzice    TABLE     �   CREATE TABLE public.rodzice (
    id_rodzica integer NOT NULL,
    imie text,
    nazwisko text,
    email text,
    haslo text
);
    DROP TABLE public.rodzice;
       public         heap    postgres    false            �            1259    35136 	   uczniowie    TABLE       CREATE TABLE public.uczniowie (
    "miejsce zamieszkania" text,
    "data urodzenia" date,
    dojezdza boolean,
    id_ucznia integer NOT NULL,
    email text NOT NULL,
    id_klasy integer,
    "miejsce urodzenia" text,
    imie text,
    nazwisko text,
    haslo text
);
    DROP TABLE public.uczniowie;
       public         heap    postgres    false            �           0    0    TABLE uczniowie    COMMENT     J   COMMENT ON TABLE public.uczniowie IS 'do przechowywania danych o uczniu';
          public          postgres    false    213            �            1259    35142    uczy    TABLE     a   CREATE TABLE public.uczy (
    id_nauczyciela integer NOT NULL,
    id_klasy integer NOT NULL
);
    DROP TABLE public.uczy;
       public         heap    postgres    false            �            1259    35145    uwagi    TABLE     �   CREATE TABLE public.uwagi (
    data date,
    id_nauczyciela integer,
    id_ucznia integer,
    id_uwagi integer NOT NULL,
    uwaga text
);
    DROP TABLE public.uwagi;
       public         heap    postgres    false            �            1259    35151 
   wiadomosci    TABLE     �   CREATE TABLE public.wiadomosci (
    wiadomosc text,
    czas time without time zone,
    data date,
    id_wiadomosci integer NOT NULL,
    autor text,
    id_nauczyciela integer NOT NULL,
    id_rodzica integer NOT NULL
);
    DROP TABLE public.wiadomosci;
       public         heap    postgres    false            t          0    35079    dzien 
   TABLE DATA           3   COPY public.dzien ("Id_dnia", "Dzien") FROM stdin;
    public          postgres    false    202   V       u          0    35085    events 
   TABLE DATA           A   COPY public.events (id_event, event, data, id_klasy) FROM stdin;
    public          postgres    false    203   WV       v          0    35091    godzina 
   TABLE DATA           W   COPY public.godzina ("Id_godzina", "Id_dnia", "Ktora_godzina", id_godziny) FROM stdin;
    public          postgres    false    204   sW       w          0    35097    klasa 
   TABLE DATA           C   COPY public.klasa (id_klasy, nazwa_klasy, rok_szkolny) FROM stdin;
    public          postgres    false    205    X       x          0    35103    lekcja 
   TABLE DATA           f   COPY public.lekcja (id_lekcji, id_godziny, id_nauczyciela, id_przedmiotu, sala, id_klasy) FROM stdin;
    public          postgres    false    206   TX       y          0    35106    nauczyciele 
   TABLE DATA           b   COPY public.nauczyciele (id_nauczyciela, imie, nazwisko, email, haslo, id_przedmiotu) FROM stdin;
    public          postgres    false    207   �Y       z          0    35112    oceny 
   TABLE DATA           g   COPY public.oceny (id_przedmiotu, data, id_oceny, rodzaj_oceny, ocena, id_ucznia, semestr) FROM stdin;
    public          postgres    false    208   [       {          0    35118    plan 
   TABLE DATA           7   COPY public.plan ("Id_klasy", "Id_lekcji") FROM stdin;
    public          postgres    false    209   �\       |          0    35121 
   przedmioty 
   TABLE DATA           E   COPY public.przedmioty (id_przedmiotu, nazwa_przedmiotu) FROM stdin;
    public          postgres    false    210   �\       }          0    35127    relacje 
   TABLE DATA           8   COPY public.relacje (id_ucznia, id_rodzica) FROM stdin;
    public          postgres    false    211   #]       ~          0    35130    rodzice 
   TABLE DATA           K   COPY public.rodzice (id_rodzica, imie, nazwisko, email, haslo) FROM stdin;
    public          postgres    false    212   c]                 0    35136 	   uczniowie 
   TABLE DATA           �   COPY public.uczniowie ("miejsce zamieszkania", "data urodzenia", dojezdza, id_ucznia, email, id_klasy, "miejsce urodzenia", imie, nazwisko, haslo) FROM stdin;
    public          postgres    false    213   r^       �          0    35142    uczy 
   TABLE DATA           8   COPY public.uczy (id_nauczyciela, id_klasy) FROM stdin;
    public          postgres    false    214   X`       �          0    35145    uwagi 
   TABLE DATA           Q   COPY public.uwagi (data, id_nauczyciela, id_ucznia, id_uwagi, uwaga) FROM stdin;
    public          postgres    false    215   �`       �          0    35151 
   wiadomosci 
   TABLE DATA           m   COPY public.wiadomosci (wiadomosc, czas, data, id_wiadomosci, autor, id_nauczyciela, id_rodzica) FROM stdin;
    public          postgres    false    216   ?a       �
           2606    35158    dzien Dzien_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.dzien
    ADD CONSTRAINT "Dzien_pkey" PRIMARY KEY ("Id_dnia");
 <   ALTER TABLE ONLY public.dzien DROP CONSTRAINT "Dzien_pkey";
       public            postgres    false    202            �
           2606    35160    klasa Klasa_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.klasa
    ADD CONSTRAINT "Klasa_pkey" PRIMARY KEY (id_klasy) INCLUDE (id_klasy);
 <   ALTER TABLE ONLY public.klasa DROP CONSTRAINT "Klasa_pkey";
       public            postgres    false    205    205            �
           2606    35162    nauczyciele PK_Id_nauczyciela 
   CONSTRAINT     i   ALTER TABLE ONLY public.nauczyciele
    ADD CONSTRAINT "PK_Id_nauczyciela" PRIMARY KEY (id_nauczyciela);
 I   ALTER TABLE ONLY public.nauczyciele DROP CONSTRAINT "PK_Id_nauczyciela";
       public            postgres    false    207            �
           2606    35164    plan Plan_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.plan
    ADD CONSTRAINT "Plan_pkey" PRIMARY KEY ("Id_klasy");
 :   ALTER TABLE ONLY public.plan DROP CONSTRAINT "Plan_pkey";
       public            postgres    false    209            �
           2606    35166    uczniowie U_id_ucznia 
   CONSTRAINT     W   ALTER TABLE ONLY public.uczniowie
    ADD CONSTRAINT "U_id_ucznia" UNIQUE (id_ucznia);
 A   ALTER TABLE ONLY public.uczniowie DROP CONSTRAINT "U_id_ucznia";
       public            postgres    false    213            �
           2606    35168    uczniowie Uczniowie_pkey 
   CONSTRAINT     s   ALTER TABLE ONLY public.uczniowie
    ADD CONSTRAINT "Uczniowie_pkey" PRIMARY KEY (id_ucznia) INCLUDE (id_ucznia);
 D   ALTER TABLE ONLY public.uczniowie DROP CONSTRAINT "Uczniowie_pkey";
       public            postgres    false    213    213            �
           2606    35170    nauczyciele Uid_nauczyciela 
   CONSTRAINT     b   ALTER TABLE ONLY public.nauczyciele
    ADD CONSTRAINT "Uid_nauczyciela" UNIQUE (id_nauczyciela);
 G   ALTER TABLE ONLY public.nauczyciele DROP CONSTRAINT "Uid_nauczyciela";
       public            postgres    false    207            �
           2606    35172    events events_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (id_event);
 <   ALTER TABLE ONLY public.events DROP CONSTRAINT events_pkey;
       public            postgres    false    203            �
           2606    35174    relacje fk_id_ucznia_id_rodzica 
   CONSTRAINT     p   ALTER TABLE ONLY public.relacje
    ADD CONSTRAINT fk_id_ucznia_id_rodzica PRIMARY KEY (id_ucznia, id_rodzica);
 I   ALTER TABLE ONLY public.relacje DROP CONSTRAINT fk_id_ucznia_id_rodzica;
       public            postgres    false    211    211            �
           2606    35176    lekcja lekcja_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.lekcja
    ADD CONSTRAINT lekcja_pkey PRIMARY KEY (id_lekcji);
 <   ALTER TABLE ONLY public.lekcja DROP CONSTRAINT lekcja_pkey;
       public            postgres    false    206            �
           2606    35178    oceny oceny_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.oceny
    ADD CONSTRAINT oceny_pkey PRIMARY KEY (id_oceny);
 :   ALTER TABLE ONLY public.oceny DROP CONSTRAINT oceny_pkey;
       public            postgres    false    208            �
           2606    35180    godzina pk_id_godzina 
   CONSTRAINT     ]   ALTER TABLE ONLY public.godzina
    ADD CONSTRAINT pk_id_godzina PRIMARY KEY ("Id_godzina");
 ?   ALTER TABLE ONLY public.godzina DROP CONSTRAINT pk_id_godzina;
       public            postgres    false    204            �
           2606    35182    przedmioty pk_id_przedmiotu 
   CONSTRAINT     d   ALTER TABLE ONLY public.przedmioty
    ADD CONSTRAINT pk_id_przedmiotu PRIMARY KEY (id_przedmiotu);
 E   ALTER TABLE ONLY public.przedmioty DROP CONSTRAINT pk_id_przedmiotu;
       public            postgres    false    210            �
           2606    35184    rodzice pk_id_rodzica 
   CONSTRAINT     [   ALTER TABLE ONLY public.rodzice
    ADD CONSTRAINT pk_id_rodzica PRIMARY KEY (id_rodzica);
 ?   ALTER TABLE ONLY public.rodzice DROP CONSTRAINT pk_id_rodzica;
       public            postgres    false    212            �
           2606    35186    uczy uczy_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.uczy
    ADD CONSTRAINT uczy_pkey PRIMARY KEY (id_nauczyciela, id_klasy);
 8   ALTER TABLE ONLY public.uczy DROP CONSTRAINT uczy_pkey;
       public            postgres    false    214    214            �
           2606    35188    uwagi uwagi_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.uwagi
    ADD CONSTRAINT uwagi_pkey PRIMARY KEY (id_uwagi);
 :   ALTER TABLE ONLY public.uwagi DROP CONSTRAINT uwagi_pkey;
       public            postgres    false    215            �
           2606    35190    wiadomosci wiadomosci_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.wiadomosci
    ADD CONSTRAINT wiadomosci_pkey PRIMARY KEY (id_wiadomosci);
 D   ALTER TABLE ONLY public.wiadomosci DROP CONSTRAINT wiadomosci_pkey;
       public            postgres    false    216            �
           1259    35191    fki_fk_id_nauczyciela    INDEX     Q   CREATE INDEX fki_fk_id_nauczyciela ON public.uwagi USING btree (id_nauczyciela);
 )   DROP INDEX public.fki_fk_id_nauczyciela;
       public            postgres    false    215            �
           2606    35192    godzina Godzina_Id_dnia_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.godzina
    ADD CONSTRAINT "Godzina_Id_dnia_fkey" FOREIGN KEY ("Id_dnia") REFERENCES public.dzien("Id_dnia") NOT VALID;
 H   ALTER TABLE ONLY public.godzina DROP CONSTRAINT "Godzina_Id_dnia_fkey";
       public          postgres    false    202    204    2753            �
           2606    35197    lekcja fk_id_godziny    FK CONSTRAINT     �   ALTER TABLE ONLY public.lekcja
    ADD CONSTRAINT fk_id_godziny FOREIGN KEY (id_godziny) REFERENCES public.godzina("Id_godzina") NOT VALID;
 >   ALTER TABLE ONLY public.lekcja DROP CONSTRAINT fk_id_godziny;
       public          postgres    false    204    206    2757            �
           2606    35202    plan fk_id_klasy    FK CONSTRAINT     �   ALTER TABLE ONLY public.plan
    ADD CONSTRAINT fk_id_klasy FOREIGN KEY ("Id_klasy") REFERENCES public.klasa(id_klasy) NOT VALID;
 :   ALTER TABLE ONLY public.plan DROP CONSTRAINT fk_id_klasy;
       public          postgres    false    205    209    2759            �
           2606    35207    uczy fk_id_klasy    FK CONSTRAINT     �   ALTER TABLE ONLY public.uczy
    ADD CONSTRAINT fk_id_klasy FOREIGN KEY (id_klasy) REFERENCES public.klasa(id_klasy) NOT VALID;
 :   ALTER TABLE ONLY public.uczy DROP CONSTRAINT fk_id_klasy;
       public          postgres    false    205    214    2759            �
           2606    35212    uczniowie fk_id_klasy    FK CONSTRAINT     �   ALTER TABLE ONLY public.uczniowie
    ADD CONSTRAINT fk_id_klasy FOREIGN KEY (id_klasy) REFERENCES public.klasa(id_klasy) NOT VALID;
 ?   ALTER TABLE ONLY public.uczniowie DROP CONSTRAINT fk_id_klasy;
       public          postgres    false    205    2759    213            �
           2606    35217    events fk_id_klasy    FK CONSTRAINT     �   ALTER TABLE ONLY public.events
    ADD CONSTRAINT fk_id_klasy FOREIGN KEY (id_klasy) REFERENCES public.klasa(id_klasy) NOT VALID;
 <   ALTER TABLE ONLY public.events DROP CONSTRAINT fk_id_klasy;
       public          postgres    false    2759    203    205            �
           2606    35222    lekcja fk_id_klasy    FK CONSTRAINT     �   ALTER TABLE ONLY public.lekcja
    ADD CONSTRAINT fk_id_klasy FOREIGN KEY (id_klasy) REFERENCES public.klasa(id_klasy) NOT VALID;
 <   ALTER TABLE ONLY public.lekcja DROP CONSTRAINT fk_id_klasy;
       public          postgres    false    206    2759    205            �
           2606    35227    uczy fk_id_nauczyciela    FK CONSTRAINT     �   ALTER TABLE ONLY public.uczy
    ADD CONSTRAINT fk_id_nauczyciela FOREIGN KEY (id_nauczyciela) REFERENCES public.nauczyciele(id_nauczyciela);
 @   ALTER TABLE ONLY public.uczy DROP CONSTRAINT fk_id_nauczyciela;
       public          postgres    false    214    2763    207            �
           2606    35232    uwagi fk_id_nauczyciela    FK CONSTRAINT     �   ALTER TABLE ONLY public.uwagi
    ADD CONSTRAINT fk_id_nauczyciela FOREIGN KEY (id_nauczyciela) REFERENCES public.nauczyciele(id_nauczyciela) NOT VALID;
 A   ALTER TABLE ONLY public.uwagi DROP CONSTRAINT fk_id_nauczyciela;
       public          postgres    false    207    215    2763            �
           2606    35237    wiadomosci fk_id_nauczyciela    FK CONSTRAINT     �   ALTER TABLE ONLY public.wiadomosci
    ADD CONSTRAINT fk_id_nauczyciela FOREIGN KEY (id_nauczyciela) REFERENCES public.nauczyciele(id_nauczyciela) NOT VALID;
 F   ALTER TABLE ONLY public.wiadomosci DROP CONSTRAINT fk_id_nauczyciela;
       public          postgres    false    216    207    2763            �
           2606    35242    lekcja fk_id_nauczyciela    FK CONSTRAINT     �   ALTER TABLE ONLY public.lekcja
    ADD CONSTRAINT fk_id_nauczyciela FOREIGN KEY (id_nauczyciela) REFERENCES public.nauczyciele(id_nauczyciela) NOT VALID;
 B   ALTER TABLE ONLY public.lekcja DROP CONSTRAINT fk_id_nauczyciela;
       public          postgres    false    206    207    2763            �
           2606    35247    oceny fk_id_przedmiotu    FK CONSTRAINT     �   ALTER TABLE ONLY public.oceny
    ADD CONSTRAINT fk_id_przedmiotu FOREIGN KEY (id_przedmiotu) REFERENCES public.przedmioty(id_przedmiotu) NOT VALID;
 @   ALTER TABLE ONLY public.oceny DROP CONSTRAINT fk_id_przedmiotu;
       public          postgres    false    210    2771    208            �
           2606    35252    lekcja fk_id_przedmiotu    FK CONSTRAINT     �   ALTER TABLE ONLY public.lekcja
    ADD CONSTRAINT fk_id_przedmiotu FOREIGN KEY (id_przedmiotu) REFERENCES public.przedmioty(id_przedmiotu) NOT VALID;
 A   ALTER TABLE ONLY public.lekcja DROP CONSTRAINT fk_id_przedmiotu;
       public          postgres    false    206    210    2771            �
           2606    35257    nauczyciele fk_id_przedmiotu    FK CONSTRAINT     �   ALTER TABLE ONLY public.nauczyciele
    ADD CONSTRAINT fk_id_przedmiotu FOREIGN KEY (id_przedmiotu) REFERENCES public.przedmioty(id_przedmiotu) NOT VALID;
 F   ALTER TABLE ONLY public.nauczyciele DROP CONSTRAINT fk_id_przedmiotu;
       public          postgres    false    210    2771    207            �
           2606    35262    relacje fk_id_rodzica    FK CONSTRAINT     �   ALTER TABLE ONLY public.relacje
    ADD CONSTRAINT fk_id_rodzica FOREIGN KEY (id_rodzica) REFERENCES public.rodzice(id_rodzica) NOT VALID;
 ?   ALTER TABLE ONLY public.relacje DROP CONSTRAINT fk_id_rodzica;
       public          postgres    false    211    2775    212            �
           2606    35267    wiadomosci fk_id_rodzica    FK CONSTRAINT     �   ALTER TABLE ONLY public.wiadomosci
    ADD CONSTRAINT fk_id_rodzica FOREIGN KEY (id_rodzica) REFERENCES public.rodzice(id_rodzica) NOT VALID;
 B   ALTER TABLE ONLY public.wiadomosci DROP CONSTRAINT fk_id_rodzica;
       public          postgres    false    2775    212    216            �
           2606    35272    relacje fk_id_ucznia    FK CONSTRAINT     �   ALTER TABLE ONLY public.relacje
    ADD CONSTRAINT fk_id_ucznia FOREIGN KEY (id_ucznia) REFERENCES public.uczniowie(id_ucznia);
 >   ALTER TABLE ONLY public.relacje DROP CONSTRAINT fk_id_ucznia;
       public          postgres    false    211    2777    213            �
           2606    35277    oceny fk_id_ucznia    FK CONSTRAINT     �   ALTER TABLE ONLY public.oceny
    ADD CONSTRAINT fk_id_ucznia FOREIGN KEY (id_ucznia) REFERENCES public.uczniowie(id_ucznia) NOT VALID;
 <   ALTER TABLE ONLY public.oceny DROP CONSTRAINT fk_id_ucznia;
       public          postgres    false    2777    213    208            �
           2606    35282    uwagi fk_id_ucznia    FK CONSTRAINT     �   ALTER TABLE ONLY public.uwagi
    ADD CONSTRAINT fk_id_ucznia FOREIGN KEY (id_ucznia) REFERENCES public.uczniowie(id_ucznia) NOT VALID;
 <   ALTER TABLE ONLY public.uwagi DROP CONSTRAINT fk_id_ucznia;
       public          postgres    false    215    213    2777            t   <   x�3����LM��L�I��2�/�/2�9���S�L8����J�B���� F� u      u     x���Mj�0���)f�B4R�4�v�M��`��"[�!��lz��c���աDP�	B ��>޼A�.����;���c���y�8���P����Zi)�Z��]5	O۪����;�k������P����v�XW��%3�͙(�o�42k�[�_p�x������=$q�x`[I`��2�`���J�'�����ˁnźե:��a˨Ħ�� ������s�l<c3����ʜF�t�D�\^� ��-2�]�c�?/��?�      v   �   x�ϹAC�X,�Rs���hXH?y�,�u���+��9�1���P-�D��)�\�Wv6�_=�*��
$�P
���N�+Np�,������I��&�]�Ռ�p����x�7��eA���f�KZ�쵠����q��&�/���=�~����#�*      w   $   x�3�4q�420��72�2�4q�s�9M21z\\\ ��      x   O  x�M�[r�0��ä����K��L�,���� F����BW��Ԛ�^x�.ԋ.�6��r�M!k�mK�i��mq�b<F)���Ҵ��*|��j�~]���rW���N-�2����Ʊ��dT�8��my�RR�%������yF��z�����]������t��r��F3^H�C�']��%�4l�v�c�F�C@a':{K����D���{��'C�T���&T���m ��J�3f�mo�`���(>1d���!+��'�V��s�q�jC.�CQ�o�Ηh��zKG|&|$_��>��`�DҡA"�^
'S}�o��H����f�-R�?����n~      y   C  x�eQAN�0<��,�&-�(�S��T$$�e�-p�đ�`�c�ĕʿX�TJՋ�;ޑgg�i{�jx֪J�+�P�o�ԖW��[�2iU�X�J,�E	&.a�U�<l=�{0S�Mn�n�V�'�
؍{����� ��le�[(X	�v�yӪGx�8�]A�4a��h'h�=�+�
t��V��U��r��戝�$�%��6J5�Dl"�o4K�:!^
ykv=[p��-��rˍwsW2ɒ%� }R5�z�j����l�%�霥̲������*}U��^��w��]��"�9�jH<P7�7�yɀ��r��N���      z   �  x�e�1N1E��)����N63g��ЌX
��E���q/�,��oi������cK�(�N�~A��z~?~}l�5�`A&�����zu|^�N�rG҈�_�pzi�v�F������==�?�O� �E�$c}O"����:�GdU�^ߏ�eT��*$�Le�㰜U��k:1M1�$�I���V�( ��	h�m]b���K���Z}�_�H��r���j��3mYVC˅��	C+�;��f��2;/3^�8#���1`�(��{g>��vɭqd� ���U���)v�f7�(]�#~�3���]�kf� n����I�;¸?�S�l�fX�.Zgb�MI�C�䟡����l�n�N��B�͌̀ԡI�wBr���)��1���4� ��f      {      x������ � �      |   [   x�3���K�/�M,��N�2��:2��2[� ?�8;�˔3����(?%�ˌ3 '��ʐ�7�$���%1/=3�ˈ�#��$�(3�+F��� �^!�      }   0   x��I   İw+�a��$����`8Y.����x���jB�+��t�      ~   �   x�mP�N1��>&����P"PD�44����y#�'�\F��AG�(���N13;�c�jMx���ۀ�T���%�F�(Ǔ鬘`���+֎�6a��♷�8<t�PG�5��pg���=5�	s�%�ik�P��!�E�uWXt���>��6/���X�a�eW�dl#���\|��dg���K�X�sH3W�X���з4��d��>�\��#�8>�wE�>9'~����G���NX7:(�Y����� ���         �  x�m��n�@���S�@,�1�ŷT�k��)�e�7�f�]w����o���-׈��,صݘB�??��3t�o���8��x��!�9B��(P�(7�Y�F�9j�.M��w�r��d4�$dJ�]��$>�b����Qsj�Ϩ��-t'�I�!����Q�J�k/����7����HY�iLHc(Т�j���Wd��
X-�.�!��솠�h�pZ��Iܝ�����6K���9(�&ւ[ܶK6���!��U�C뤸p=v,��G�?�h|��O�%�8�u�j����U¡�{'(	?knˆ��FS�����\�,a�U�{���I��oO���鉮���i �����VJHD����/�'ftfCV�PM+�s�`4kո����X�*n�-4��W�O�k�kp�x����fSiT�;�2
S
�������䮌��l����د���OQ
�-�      �      x�3�4�2�4�2cc 6����� '��      �   �   x�3202�50�5��4�4���Û����L".#�����9�!Pސө(1[��(1�R!%?7�<5U�!�A5F@�� �0�ʯ�M,�LT�KT�I�N�ʄ�14 �0�4�g��9�Gf��1�����a���(�J-��NL��f��	�#N����J�<Қ�4I]� ��a�      �   �   x���;
�0��99���J��0��
���K��GR�if/�}�^���E<���/�J>�p6��p��h�)j٬�yDx�#��$EmM#4����@!����hf�����hU���v�r�F��O��L���%윕e-�����!��/�%c���6Z����(���Nq��l�R>�����lB�����U�     