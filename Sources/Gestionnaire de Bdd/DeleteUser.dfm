object DeleteUserForm: TDeleteUserForm
  Left = 0
  Top = 0
  Caption = 'Supprimer un utilisateur'
  ClientHeight = 149
  ClientWidth = 360
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object lblTitreDeleteUser: TLabel
    Left = 0
    Top = 0
    Width = 360
    Height = 24
    Align = alTop
    Alignment = taCenter
    Caption = 'Suppression d'#39'un utilisateur'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -20
    Font.Name = 'Tahoma'
    Font.Style = []
    ParentFont = False
    ExplicitWidth = 245
  end
  object PanelBas: TPanel
    Left = 0
    Top = 108
    Width = 360
    Height = 41
    Align = alBottom
    TabOrder = 0
    object Button1: TButton
      Left = 112
      Top = 6
      Width = 145
      Height = 27
      Caption = 'Suppression de l'#39'utilisateur'
      TabOrder = 0
      OnClick = Button1Click
    end
  end
  object PanelGauche: TPanel
    Left = 0
    Top = 24
    Width = 360
    Height = 84
    Align = alClient
    TabOrder = 1
    object Panel6: TPanel
      Left = 1
      Top = 1
      Width = 358
      Height = 24
      Align = alTop
      TabOrder = 0
      object lblchoixId: TLabel
        Left = 1
        Top = 1
        Width = 215
        Height = 22
        Align = alLeft
        Alignment = taCenter
        Anchors = [akLeft, akTop, akRight, akBottom]
        AutoSize = False
        Caption = 'Identifiant de l'#39'utilisateur : '
        EllipsisPosition = epEndEllipsis
        Font.Charset = DEFAULT_CHARSET
        Font.Color = clWindowText
        Font.Height = -11
        Font.Name = 'Tahoma'
        Font.Style = []
        ParentFont = False
        ExplicitWidth = 144
        ExplicitHeight = 39
      end
      object cbIdUser: TComboBox
        Left = 216
        Top = 1
        Width = 141
        Height = 21
        Align = alRight
        TabOrder = 0
      end
    end
  end
end
